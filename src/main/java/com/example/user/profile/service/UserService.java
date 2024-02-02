package com.example.user.profile.service;

import com.example.user.profile.dao.entity.User;
import com.example.user.profile.service.request.CreateUserServiceRequest;
import com.example.user.profile.service.request.UserServiceRequest;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface UserService {

    User getUser(UserServiceRequest serviceRequest);

    void createUser(CreateUserServiceRequest serviceRequest);
}
