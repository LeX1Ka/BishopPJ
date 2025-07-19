package org.example.synthetichumancorestarter.audit;

public class ConsoleAuditPublisher implements AuditPublisher {

    @Override
    public void publish(AuditEvent auditEvent) {
        System.out.println("Audit Event: " + auditEvent);
    }
}
