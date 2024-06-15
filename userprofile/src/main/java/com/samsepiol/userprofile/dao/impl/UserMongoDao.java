package com.samsepiol.userprofile.dao.impl;

import com.samsepiol.userprofile.dao.config.MongoConfig;
import com.samsepiol.userprofile.dao.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class UserMongoDao extends BaseMongoDao<String, User> {
    public UserMongoDao(MongoConfig mongoConfig) {
        super(User.class, mongoConfig);
    }
}
