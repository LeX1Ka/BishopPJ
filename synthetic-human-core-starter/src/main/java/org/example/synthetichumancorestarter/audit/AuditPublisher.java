package org.example.synthetichumancorestarter.audit;

public interface AuditPublisher {
    public void publish(AuditEvent auditEvent);
}
