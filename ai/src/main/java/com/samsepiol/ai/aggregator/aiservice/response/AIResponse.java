package com.samsepiol.ai.aggregator.aiservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse implements Serializable {
    private String message;

    public static AIResponse empty() {
        return AIResponse.builder().build();
    }
}
