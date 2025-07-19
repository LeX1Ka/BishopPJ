package org.example.synthetichumancorestarter.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "audit")
public class AuditProperties {
    private String mode = "console"; // console | kafka
    private String kafkaTopic = "audit-topic";
}
