package com.magic.crius.dao.base;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.util.ReflectionUtils;
import com.magic.crius.vo.ReqQueryVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public abstract class BaseMongoDAOImpl<T> implements BaseMongoDAO<T> {

    private static final int DEFAULT_SKIP = 0;
    private static final int DEFAULT_LIMIT = 200;

    @Override
    public List<T> find(Query query) {
        return getMongoTemplate().find(query, this.getEntityClass());
    }

    public List<T> find(Query query, String collectionName) {
        return getMongoTemplate().find(query, this.getEntityClass(), collectionName);
    }

    @Override
    public T findOne(Query query) {
        return getMongoTemplate().findOne(query, this.getEntityClass());
    }

    @Override
    public T findOne(Query query, String collectionName) {
        return getMongoTemplate().findOne(query, this.getEntityClass(), collectionName);
    }

    @Override
    public T update(Query query, Update update) {
        return getMongoTemplate().findAndModify(query, update, this.getEntityClass());
    }

    @Override
    public T save(T entity) {
        getMongoTemplate().insert(entity);
        return entity;
    }

    @Override
    public <X> boolean save(Collection<X> objects, String collectionName) {
        try {
            getMongoTemplate().insert(objects, collectionName);
            return true;
        } catch (Exception e) {
            ApiLogger.error("mongoDao save error, collectionName : "+ collectionName,e);
        }
        return false;
    }

    @Override
    public T save(T entity, String collectionName) {
        getMongoTemplate().insert(entity, collectionName);
        return entity;
    }


    @Override
    public T findById(String id) {
        return getMongoTemplate().findById(id, this.getEntityClass());
    }

    @Override
    public T findById(String id, String collectionName) {
        return getMongoTemplate().findById(id, this.getEntityClass(), collectionName);
    }

    @Override
    public long count(Query query) {
        return getMongoTemplate().count(query, this.getEntityClass());
    }


    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo, String collectionName) {
        ApiLogger.info("baseMongo getSucIds, collectionName : "+ collectionName +", queryVo : "+ JSON.toJSONString(queryVo));
        List<Long> reqIds = new ArrayList<>();
        BasicDBObject condition = new BasicDBObject();
        condition.append("produceTime", new BasicDBObject("$gte", queryVo.getStartTime()).append("$lt", queryVo.getEndTime()));
        BasicDBObject keys = new BasicDBObject();
        keys.append("reqId", 1);
        Iterator<DBObject> iterator = getMongoTemplate().getCollection(collectionName).find(condition, keys);
        while (iterator.hasNext()) {
            Long reqId = (Long) iterator.next().get("reqId");
            reqIds.add(reqId);
        }
        return reqIds;
    }

    @Override
    public List<T> getNotProc(ReqQueryVo queryVo, String collectionName, Pageable pageable) {
        Query query = new Query();
        if (queryVo.getReqIds() != null && queryVo.getReqIds().size() > 0) {
            query.addCriteria(new Criteria("reqId").nin(queryVo.getReqIds()));
        }
        query.addCriteria(new Criteria("produceTime").gte(queryVo.getStartTime()).lt(queryVo.getEndTime()));
        if (pageable != null) {
            query.skip(pageable.getOffset()).limit(pageable.getPageSize());
            query.with(pageable.getSort());
        }
        ApiLogger.info("baseMongo getNotProc, collectionName : "+ collectionName +", query : "+ JSON.toJSONString(query));
        List<T> result =find(query,collectionName);
        ApiLogger.info("baseMongo getNotProc, collectionName : "+ collectionName +", result : "+ JSON.toJSONString(result));
        return result;
    }

    @Override
    public List<T> getSaveFailed(Long startTime, Long endTime, String collectionName) {
        Query query = new Query();
        query.addCriteria(new Criteria("produceTime").gte(startTime).lt(endTime));
        return find(query, MongoCollectionFlag.MONGO_FAILED.collName(collectionName));
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }

    public abstract MongoTemplate getMongoTemplate();
}
