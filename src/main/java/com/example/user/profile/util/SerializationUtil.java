package com.example.user.profile.util;

import com.example.user.profile.exception.SerializationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class SerializationUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String convertToString(Object object) {
        try {
            return object instanceof String string ? string : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw SerializationException.build();
        }
    }

    public static Map<String, Object> convertToMap(Object object) {
        if (object instanceof Map) {
            return (Map<String, Object>) object;
        }
        return objectMapper.convertValue(object,
                TypeFactory.defaultInstance().constructMapLikeType(HashMap.class, String.class, Object.class));
    }

    public static <T> T convertToEntity(Object object, Class<T> cls) {
        try {
            return objectMapper.readValue(convertToString(object), cls);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw SerializationException.build();
        }
    }

}
