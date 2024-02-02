package com.example.user.profile.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@SuperBuilder(builderMethodName = "parentBuilder")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserServiceRequest implements Serializable {
    private final String userId;
}
