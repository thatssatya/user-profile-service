package com.example.user.profile.dao.impl;

import com.example.user.profile.dao.MongoDao;
import com.example.user.profile.dao.config.MongoConfig;
import com.example.user.profile.dao.request.MongoBaseRequest;
import com.example.user.profile.dao.request.MongoInsertOrUpdateRequest;
import com.example.user.profile.exception.UserProfileException;
import com.example.user.profile.model.Error;
import com.example.user.profile.util.SerializationUtil;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Objects;

@Slf4j
public abstract class BaseMongoDao<K, V> implements MongoDao<K, V> {
    private final Class<V> vClass;
    private final MongoConfig mongoConfig;
    private static MongoClient mongoClient = null;

    protected BaseMongoDao(Class<V> vClass, MongoConfig mongoConfig) {
        this.vClass = vClass;
        this.mongoConfig = mongoConfig;
    }

    private MongoClient mongoClient() {
        if (Objects.isNull(mongoClient)) {
            synchronized (BaseMongoDao.class) {
                if (Objects.isNull(mongoClient)) {
                    mongoClient = MongoClients.create(mongoConfig.getConnectionString());
                    log.info("Mongo initialized!");
                }
            }
        }
        return mongoClient;
    }

    @Override
    public V get(MongoBaseRequest request) {
        try {
            var collection = getCollection(request.getCollectionName());
            var entity = collection.find(Filters.eq(mongoConfig.getIndexKey(), request.getKey())).first();
            log.info("DB fetch succeeded!");
            return SerializationUtil.convertToEntity(entity, vClass);
        } catch (MongoException exception) {
            log.error("Mongo Exception: {}", exception.getMessage());
            throw UserProfileException.fromErrorCode(Error.DB_FETCH_ERROR);
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            throw UserProfileException.fromErrorCode(Error.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void insert(MongoInsertOrUpdateRequest request) {
        try {
            var collection = getCollection(request.getCollectionName());
            var document = Document.parse(SerializationUtil.convertToString(request.getValue()));
            document.put(mongoConfig.getIndexKey(), request.getKey());
            collection.insertOne(document);
            log.info("DB insert succeeded!");
        } catch (MongoException exception) {
            log.error("Mongo Exception: {}", exception.getMessage());
            throw UserProfileException.fromErrorCode(Error.DB_INSERTION_ERROR);
        } catch (Exception exception) {
            log.error("Exception: {}", exception.getMessage());
            throw UserProfileException.fromErrorCode(Error.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void close() {
        mongoClient().close();
    }

    private MongoCollection<Document> getCollection(String collectionName) {
        return mongoClient().getDatabase(mongoConfig.getDatabase()).getCollection(collectionName);
    }

}
