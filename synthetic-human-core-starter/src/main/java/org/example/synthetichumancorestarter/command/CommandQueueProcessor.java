package org.example.synthetichumancorestarter.command;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class CommandQueueProcessor {
    BlockingQueue<CommandRequest> queue = new ArrayBlockingQueue<>(100);

    public void enqueue(CommandRequest command) {
        boolean success = queue.offer(command);

        if (!success) {
            throw new QueueOverflowException("Очередь переполнена");
        }
    }

    @PostConstruct
    public void process() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    CommandRequest command = queue.take();
                    System.out.println("Выполнение COMMON команды: " + command.getDescription());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
