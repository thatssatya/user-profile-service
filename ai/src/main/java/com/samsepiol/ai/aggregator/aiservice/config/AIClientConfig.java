package com.samsepiol.ai.aggregator.aiservice.config;

import com.samsepiol.bom.library.http.constants.HttpConstants;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "ai-client-config")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AIClientConfig {
    String origin;
    String authorization;

    public Map<String, String> getHeaders() {
        return Map.of(
                HttpConstants.Headers.ORIGIN, origin,
                HttpConstants.Headers.AUTHORIZATION, authorization
        );
    }
}
