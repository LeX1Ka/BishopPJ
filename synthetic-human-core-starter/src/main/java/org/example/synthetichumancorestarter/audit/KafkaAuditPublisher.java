package org.example.synthetichumancorestarter.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaAuditPublisher implements AuditPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic;
    private final ObjectMapper mapper;

    public KafkaAuditPublisher(KafkaTemplate<String, String> kafkaTemplate, String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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
