package com.example.user.profile.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String userId;
    private String name;
    private Integer age;
}
