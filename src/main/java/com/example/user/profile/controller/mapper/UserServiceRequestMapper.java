package com.example.user.profile.controller.mapper;

import com.example.user.profile.model.request.CreateUserRequest;
import com.example.user.profile.service.request.CreateUserServiceRequest;
import com.example.user.profile.service.request.UserServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserServiceRequestMapper {
    UserServiceRequestMapper USER_SERVICE_REQUEST_MAPPER = Mappers.getMapper(UserServiceRequestMapper.class);

    CreateUserServiceRequest from(CreateUserRequest request);

    UserServiceRequest from(String userId);

}
