package com.example.user.profile.temporal.workflow.impl;

import com.example.user.profile.constants.TaskQueue;
import com.example.user.profile.dao.entity.User;
import com.example.user.profile.service.UserService;
import com.example.user.profile.service.request.CreateUserServiceRequest;
import com.example.user.profile.temporal.workflow.CreateUserWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

public class BaseCreateUserWorkflow implements CreateUserWorkflow {

    private static final UserService userService = Workflow.newActivityStub(
            UserService.class,
            ActivityOptions.newBuilder()
                    .setTaskQueue(TaskQueue.CREATE_USER)
                    .setStartToCloseTimeout(Duration.ofSeconds(5))
                    .build());

    @Override
    public User create(CreateUserServiceRequest serviceRequest) {
        var user = buildUser(serviceRequest);
        createAsync(serviceRequest);
        return user;
    }

    private static void createAsync(CreateUserServiceRequest serviceRequest) {
        Async.procedure(userService::createUser, serviceRequest);
    }

    private static User buildUser(CreateUserServiceRequest serviceRequest) {
        return User.builder()
                .userId(getUserId(serviceRequest))
                .name(serviceRequest.getName())
                .dob(serviceRequest.getDob())
                .nationalId(serviceRequest.getNationalId())
                .build();
    }

    private static String getUserId(CreateUserServiceRequest serviceRequest) {
        return Objects.requireNonNullElse(serviceRequest.getUserId(), UUID.randomUUID().toString());
    }
}
