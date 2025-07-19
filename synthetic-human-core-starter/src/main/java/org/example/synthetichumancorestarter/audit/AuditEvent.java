    package org.example.synthetichumancorestarter.audit;

    import lombok.Getter;
    import lombok.Setter;
    import lombok.ToString;

    import java.time.Instant;

    @Getter
    @Setter
    @ToString
    public class AuditEvent {
        private String className;
        private String methodName;
        private Object[] arguments;
        private Object result;
        private Instant timestamp;
    }
