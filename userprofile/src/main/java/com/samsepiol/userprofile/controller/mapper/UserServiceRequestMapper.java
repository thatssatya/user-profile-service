package com.samsepiol.userprofile.controller.mapper;

import com.samsepiol.userprofile.model.request.CreateUserRequest;
import com.samsepiol.userprofile.service.request.CreateUserServiceRequest;
import com.samsepiol.userprofile.service.request.UserServiceRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserServiceRequestMapper {
    UserServiceRequestMapper USER_SERVICE_REQUEST_MAPPER = Mappers.getMapper(UserServiceRequestMapper.class);

    CreateUserServiceRequest from(CreateUserRequest request);

    UserServiceRequest from(String userId);

}
