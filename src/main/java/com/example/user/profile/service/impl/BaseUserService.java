package com.example.user.profile.service.impl;

import com.example.user.profile.constants.CollectionName;
import com.example.user.profile.dao.entity.User;
import com.example.user.profile.dao.impl.UserMongoDao;
import com.example.user.profile.dao.request.MongoBaseRequest;
import com.example.user.profile.dao.request.MongoInsertOrUpdateRequest;
import com.example.user.profile.exception.UserNotFoundException;
import com.example.user.profile.service.UserService;
import com.example.user.profile.service.request.CreateUserServiceRequest;
import com.example.user.profile.service.request.UserServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BaseUserService implements UserService {

    private final UserMongoDao userMongoDao;

    @Override
    public User getUser(UserServiceRequest serviceRequest) {
        var user = userMongoDao.get(MongoBaseRequest.parentBuilder()
                .key(serviceRequest.getUserId())
                .collectionName(CollectionName.USERS)
                .build());

        return Objects.requireNonNullElseGet(user, () -> {
            throw UserNotFoundException.build();
        });
    }

    @Override
    public void createUser(CreateUserServiceRequest serviceRequest) {
        var user = buildUser(serviceRequest);
        userMongoDao.insert(buildInsertRequest(user));
    }

    private static MongoInsertOrUpdateRequest buildInsertRequest(User user) {
        return MongoInsertOrUpdateRequest.builder()
                .collectionName(CollectionName.USERS)
                .key(user.getUserId())
                .value(user)
                .build();
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
