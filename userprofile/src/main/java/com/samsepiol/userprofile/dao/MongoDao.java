package com.samsepiol.userprofile.dao;

import com.samsepiol.userprofile.dao.request.MongoBaseRequest;
import com.samsepiol.userprofile.dao.request.MongoInsertOrUpdateRequest;

public interface MongoDao<K, V> {
    V get(MongoBaseRequest request);

    void insert(MongoInsertOrUpdateRequest request);

    void update();

    void close();
}
