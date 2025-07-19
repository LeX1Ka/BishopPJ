package org.example.synthetichumancorestarter.command;

import org.springframework.stereotype.Component;

@Component
public class DefaultCommandGateway implements CommandGateway {
    private final CommandQueueProcessor commandQueueProcessor;

    public DefaultCommandGateway(CommandQueueProcessor commandQueueProcessor) {
        this.commandQueueProcessor = commandQueueProcessor;
    }

    @Override
    public void acceptCommand(CommandRequest command) {
        if (command.getPriority() == CommandPriority.CRITICAL) {
            System.out.println("Critical Command");
        } else if (command.getPriority() == CommandPriority.COMMON) {
            commandQueueProcessor.enqueue(command);
        }
    }
}
