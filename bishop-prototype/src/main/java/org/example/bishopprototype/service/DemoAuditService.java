package org.example.bishopprototype.service;

import org.example.synthetichumancorestarter.audit.WeylandWatchingYou;
import org.springframework.stereotype.Component;

@Component
public class DemoAuditService {
    @WeylandWatchingYou
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
