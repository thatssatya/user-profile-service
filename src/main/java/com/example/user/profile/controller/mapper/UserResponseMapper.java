package com.example.user.profile.controller.mapper;

import com.example.user.profile.dao.entity.User;
import com.example.user.profile.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper USER_RESPONSE_MAPPER = Mappers.getMapper(UserResponseMapper.class);

    UserResponse from(User user);
}
