package com.samsepiol.userprofile.service.impl;

import com.samsepiol.userprofile.constants.CollectionName;
import com.samsepiol.userprofile.dao.entity.User;
import com.samsepiol.userprofile.dao.impl.UserMongoDao;
import com.samsepiol.userprofile.dao.request.MongoBaseRequest;
import com.samsepiol.userprofile.dao.request.MongoInsertOrUpdateRequest;
import com.samsepiol.userprofile.exception.UserNotFoundException;
import com.samsepiol.userprofile.service.UserService;
import com.samsepiol.userprofile.service.request.CreateUserServiceRequest;
import com.samsepiol.userprofile.service.request.UserServiceRequest;
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
