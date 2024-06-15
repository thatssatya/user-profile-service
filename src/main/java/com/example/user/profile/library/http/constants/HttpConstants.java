package com.example.user.profile.library.http.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHeaders;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpConstants {

    public static class Headers {
        public static final String AUTHORIZATION = HttpHeaders.AUTHORIZATION;
        public static final String CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
        public static final String ORIGIN = "Origin";
    }
}
