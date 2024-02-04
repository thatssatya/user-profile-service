package com.example.user.profile.dao.impl;

import com.example.user.profile.dao.config.MongoConfig;
import com.example.user.profile.dao.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class UserMongoDao extends BaseMongoDao<String, User> {
    public UserMongoDao(MongoConfig mongoConfig) {
        super(User.class, mongoConfig);
    }
}
