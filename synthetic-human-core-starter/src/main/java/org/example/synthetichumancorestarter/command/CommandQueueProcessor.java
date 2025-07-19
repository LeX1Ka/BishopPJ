package org.example.synthetichumancorestarter.Comman;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CommandQueueProcessor {
    BlockingQueue<CommandRequest> queue = new ArrayBlockingQueue<>(100);
    private final MeterRegistry meterRegistry;
    private final Map<String, Counter> authorCounter = new ConcurrentHashMap<>();

    public CommandQueueProcessor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.meterRegistry.gauge("queue.size", queue, BlockingQueue::size);
    }

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

                    String author = command.getAuthor();
                    Counter counter = meterRegistry.counter("commands.executed.by.author", "author", author);
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
