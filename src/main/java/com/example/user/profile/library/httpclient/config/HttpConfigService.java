package com.example.user.profile.library.httpclient.config;

import java.util.Map;

public interface HttpConfigService {
    Map<String, HttpConfig.ServiceConfig> getConfig();
}
