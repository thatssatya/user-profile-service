package com.samsepiol.ai.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AIApiRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5416985590270779667L;

    @NotNull
    String person;
    @NotNull
    String message;
    String mood;
}
