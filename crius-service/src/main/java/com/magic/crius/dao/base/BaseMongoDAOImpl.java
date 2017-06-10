package com.magic.crius.dao.base;

import com.magic.crius.util.ReflectionUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


public abstract class BaseMongoDAOImpl<T> implements BaseMongoDAO<T> {

    private static final int DEFAULT_SKIP = 0;
    private static final int DEFAULT_LIMIT = 200;

    /**
     * spring mongodb　集成操作类
     */
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, this.getEntityClass());
    }

    public List<T> find(Query query, String collectionName) {
        return mongoTemplate.find(query, this.getEntityClass(), collectionName);
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    @Override
    public T update(Query query, Update update) {
        return mongoTemplate.findAndModify(query, update, this.getEntityClass());
    }

    @Override
    public T save(T entity) {
        mongoTemplate.insert(entity);
        return entity;
    }

    @Override
    public <X> boolean save(Collection<X> objects, String collectionName) {
        try {
            mongoTemplate.insert(objects, collectionName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T save(T entity, String collectionName) {
        mongoTemplate.insert(entity, collectionName);
        return entity;
    }


    @Override
    public T findById(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, this.getEntityClass());
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }

    public MongoTemplate getMongoTemplate() {
        return this.mongoTemplate;
    }
}
