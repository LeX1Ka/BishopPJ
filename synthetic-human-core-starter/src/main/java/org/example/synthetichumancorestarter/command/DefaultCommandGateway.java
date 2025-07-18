package org.example.synthetichumancorestarter.command;

import org.springframework.stereotype.Component;

@Component
public class DefaultCommandGateway implements CommandGateway {
    @Override
    public void acceptCommand(CommandRequest command) {
        if (command.getPriority() == CommandPriority.CRITICAL) {
            System.out.println("Critical Command");
        } else if (command.getPriority() == CommandPriority.COMMON) {
            System.out.println("Common Command");
        }
    }
}
