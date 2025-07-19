package org.example.bishopprototype.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.synthetichumancorestarter.command.CommandGateway;
import org.example.synthetichumancorestarter.command.CommandRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/command")
@RequiredArgsConstructor
public class CommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public String accept(@Valid @RequestBody CommandRequest request) {
        commandGateway.acceptCommand(request);
        return "Команда получена: " + request.getDescription();
    }
}
