package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OperateWithDrawReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.OperateWithDrawReqMongoService;
import com.magic.crius.vo.OperateWithDrawReq;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:39
 */
@Service
public class OperateWithDrawReqMongoServiceImpl implements OperateWithDrawReqMongoService {

    @Resource
    private OperateWithDrawReqMongoDao operateWithDrawReqMongoDao;

    @Override
    public boolean save(OperateWithDrawReq req) {
        try {
            return operateWithDrawReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(OperateWithDrawReq req) {
        try {
            return operateWithDrawReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.operateWithDrawReq.name())) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OperateWithDrawReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return operateWithDrawReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(Collection<OperateWithDrawReq> reqs) {
        try {
            return operateWithDrawReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.operateWithDrawReq.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return operateWithDrawReqMongoDao.getSucIds(startTime, endTime, MongoCollections.operateWithDrawReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OperateWithDrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        try {
            operateWithDrawReqMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.operateWithDrawReq.name(), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OperateWithDrawReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            operateWithDrawReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.operateWithDrawReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
