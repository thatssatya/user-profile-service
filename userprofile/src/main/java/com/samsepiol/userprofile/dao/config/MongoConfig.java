package com.samsepiol.userprofile.dao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "mongo-config")
@Data
@Primary
public class MongoConfig {
    private static final String CONNECTION_STRING_PLACEHOLDER = "%s://%s:%s@%s/?%s";
    private String protocol;
    private String database;
    private String password;
    private String host;
    private String props;
    private String indexKey;

    public String getConnectionString() {
        return String.format(CONNECTION_STRING_PLACEHOLDER, protocol, database, password, host, props);
    }
}
