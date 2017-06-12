package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.CashbackReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.CashbackReqMongoService;
import com.magic.crius.vo.CashbackReq;
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
 * Date: 2017/6/5
 * Time: 16:40
 */
@Service
public class CashbackReqMongoServiceImpl implements CashbackReqMongoService {

    @Resource
    private CashbackReqMongoDao cashbackReqMongoDao;

    @Override
    public boolean save(CashbackReq req) {
        try {
            return cashbackReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(CashbackReq req) {
        try {
            return cashbackReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("cashbackReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CashbackReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return cashbackReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(Collection<CashbackReq> reqs) {
        try {
            return cashbackReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName("cashbackReq"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return cashbackReqMongoDao.getSucIds(startTime, endTime, MongoCollections.cashbackReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CashbackReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        try {
            cashbackReqMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.cashbackReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CashbackReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            cashbackReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.cashbackReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
