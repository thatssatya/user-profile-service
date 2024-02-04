package com.example.user.profile.library.http.config;

import java.util.Map;

public interface HttpConfigService {
    Map<String, HttpConfig.ServiceConfig> getConfig();
}
