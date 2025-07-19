package org.example.bishopprototype.controller;

import lombok.RequiredArgsConstructor;
import org.example.synthetichumancorestarter.command.CommandGateway;
import org.example.synthetichumancorestarter.command.CommandRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommandController {
    private final CommandGateway commandGateway;

    @PostMapping("/command")
    public void handleCommand(@RequestBody CommandRequest commandRequest) {
        commandGateway.acceptCommand(commandRequest);
    }


}
