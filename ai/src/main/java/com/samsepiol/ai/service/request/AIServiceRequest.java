package com.samsepiol.ai.service.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AIServiceRequest {
    @NonNull
    String person;

    @NonNull
    String message;

    String mood;

    Integer count;

}
