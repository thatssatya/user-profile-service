package com.example.user.profile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class DefaultMongoConfig implements MongoConfig {
    private static final String PROTOCOL = "mongodb+srv";
    private static final String DB = "Cluster15382";
    private static final String PASS = "hellorsatya";
    private static final String HOST = "cluster15382.nl5hqoa.mongodb.net";
    private static final String PROPS = "retryWrites=true&w=majority";
    private static final String CONNECTION_STRING_PLACEHOLDER = "%s://%s:%s@%s/?%s";
    private static final String INDEX_KEY = "_id";

    public String getConnectionString() {
        return String.format(CONNECTION_STRING_PLACEHOLDER, PROTOCOL, DB, PASS, HOST, PROPS);
    }

    @Override
    public String getDatabaseName() {
        return DB;
    }

    @Override
    public String getIndexKey() {
        return INDEX_KEY;
    }
}
