package org.example.synthetichumancorestarter.audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "audit")
public class AuditProperties {

    private String mode;
    private String kafkaTopic;

}
