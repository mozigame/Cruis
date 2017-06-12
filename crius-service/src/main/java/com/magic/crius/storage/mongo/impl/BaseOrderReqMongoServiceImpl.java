package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.BaseOrderReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.BaseOrderReqMongoService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:11
 */
@Service
public class BaseOrderReqMongoServiceImpl implements BaseOrderReqMongoService {

    @Resource
    private BaseOrderReqMongoDao baseOrderReqMongoDao;

    @Override
    public boolean save(BaseOrderReq req) {
        try {
            return baseOrderReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(BaseOrderReq req) {
        try {
            return baseOrderReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.baseOrderReq.name())) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseOrderReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return baseOrderReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return baseOrderReqMongoDao.getSucIds(startTime, endTime, MongoCollections.baseOrderReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BaseOrderReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        try {
            baseOrderReqMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.baseOrderReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            baseOrderReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.baseOrderReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(Collection<BaseOrderReq> reqs) {
        try {
            return baseOrderReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.baseOrderReq.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
