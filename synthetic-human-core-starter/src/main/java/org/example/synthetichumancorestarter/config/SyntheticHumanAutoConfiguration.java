package org.example.synthetichumancorestarter.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.example.synthetichumancorestarter.audit.*;
import org.example.synthetichumancorestarter.command.CommandGateway;
import org.example.synthetichumancorestarter.command.CommandQueueProcessor;
import org.example.synthetichumancorestarter.command.DefaultCommandGateway;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@EnableConfigurationProperties(AuditProperties.class)
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
    public AuditAspect auditAspect(AuditPublisher auditPublisher) {
        return new AuditAspect(auditPublisher);
    }

    @Bean
    public AuditPublisher auditPublisher(
        AuditProperties auditProperties,
        KafkaTemplate<String, String> kafkaTemplate
) {

        if ("kafka".equalsIgnoreCase(auditProperties.getMode())) {
            return new KafkaAuditPublisher(kafkaTemplate, auditProperties.getKafkaTopic());
        } else {
            return new ConsoleAuditPublisher();
        }
    }

}
