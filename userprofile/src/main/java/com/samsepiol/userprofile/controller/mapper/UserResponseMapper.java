package com.samsepiol.userprofile.controller.mapper;

import com.samsepiol.userprofile.dao.entity.User;
import com.samsepiol.userprofile.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {
    UserResponseMapper USER_RESPONSE_MAPPER = Mappers.getMapper(UserResponseMapper.class);

    UserResponse from(User user);
}
