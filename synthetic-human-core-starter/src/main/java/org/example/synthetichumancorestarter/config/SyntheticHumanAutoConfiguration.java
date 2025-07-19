package org.example.synthetichumancorestarter.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.example.synthetichumancorestarter.audit.AuditAspect;
import org.example.synthetichumancorestarter.command.CommandGateway;
import org.example.synthetichumancorestarter.command.CommandQueueProcessor;
import org.example.synthetichumancorestarter.command.DefaultCommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SyntheticHumanAutoConfiguration {

    @Bean
    public CommandQueueProcessor commandQueueProcessor(MeterRegistry meterRegistry) {
        return new CommandQueueProcessor(meterRegistry);
    }

    @Bean
    public CommandGateway commandGateway(CommandQueueProcessor commandQueueProcessor) {
        return new DefaultCommandGateway(commandQueueProcessor);
    }

    @Bean
    public AuditAspect auditAspect() {
        return new AuditAspect();
    }

}
