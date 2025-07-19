package org.example.bishopprototype.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.synthetichumancorestarter.command.CommandGateway;
import org.example.synthetichumancorestarter.command.CommandRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommandController {

    private final CommandGateway commandGateway;

    @PostMapping("/command")
    public void handleCommand(@Valid @RequestBody CommandRequest commandRequest) {
        commandGateway.acceptCommand(commandRequest);
    }
}

