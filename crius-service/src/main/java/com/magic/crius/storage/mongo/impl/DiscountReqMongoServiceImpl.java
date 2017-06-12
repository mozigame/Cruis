package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.DiscountReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.DiscountReqMongoService;
import com.magic.crius.vo.DiscountReq;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:24
 */
@Service
public class DiscountReqMongoServiceImpl implements DiscountReqMongoService {

    @Resource
    private DiscountReqMongoDao discountReqMongoDao;

    @Override
    public boolean save(DiscountReq discountReq) {
        return discountReqMongoDao.save(discountReq) != null;
    }

    @Override
    public boolean saveFailedData(DiscountReq discountReq) {
        try {
            return discountReqMongoDao.save(discountReq, MongoCollectionFlag.MONGO_FAILED.collName("discountReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DiscountReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return discountReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return discountReqMongoDao.getSucIds(startTime, endTime, MongoCollections.discountReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        try {
            return discountReqMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.discountReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return discountReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.discountReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<DiscountReq> reqs) {
        try {
            return discountReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.discountReq.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
