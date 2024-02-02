package com.example.user.profile.dao;

import com.example.user.profile.dao.request.MongoBaseRequest;
import com.example.user.profile.dao.request.MongoInsertOrUpdateRequest;

public interface MongoDao<K, V> {
    V get(MongoBaseRequest request);

    void insert(MongoInsertOrUpdateRequest request);

    void update();

    void close();
}
