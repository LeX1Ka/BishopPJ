package org.example.synthetichumancorestarter.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaAuditPublisher implements AuditPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic;
    private final ObjectMapper mapper = new ObjectMapper();

    public KafkaAuditPublisher(KafkaTemplate<String, String> kafkaTemplate, String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }
    @Override
    public void publish(AuditEvent auditEvent) {
        try {
            String json = mapper.writeValueAsString(auditEvent);
            kafkaTemplate.send(kafkaTopic, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации AuditEvent", e);
        }
    }
}
