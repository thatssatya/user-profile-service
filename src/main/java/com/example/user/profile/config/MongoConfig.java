package com.example.user.profile.config;

public interface MongoConfig {
    String getConnectionString();
    String getDatabaseName();
    String getIndexKey();
}
