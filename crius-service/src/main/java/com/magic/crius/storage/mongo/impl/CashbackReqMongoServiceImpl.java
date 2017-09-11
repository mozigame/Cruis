package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.CashbackReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.CashbackReqMongoService;
import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.ReqQueryVo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.domain.Pageable;
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
            return cashbackReqMongoDao.save(req, MongoCollectionFlag.dateCollName(MongoCollections.cashbackReq, req.getPdate())) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(CashbackReq req) {
        try {
            return cashbackReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.cashbackReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CashbackReq getByReqId(CashbackReq req) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(req.getReqId()));
            return cashbackReqMongoDao.findOne(query, MongoCollectionFlag.dateCollName(MongoCollections.cashbackReq, req.getPdate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<CashbackReq> reqs) {
        try {
            String collName = MongoCollectionFlag.dateCollName(MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.cashbackReq), reqs.get(0).getPdate());
            return cashbackReqMongoDao.save(reqs, collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            String collName = MongoCollectionFlag.dateCollName(MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.cashbackReq), queryVo.getPdate());
            return cashbackReqMongoDao.getSucIds(queryVo, collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CashbackReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return cashbackReqMongoDao.getNotProc(queryVo, MongoCollectionFlag.dateCollName(MongoCollections.cashbackReq, queryVo.getPdate()), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CashbackReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return cashbackReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.cashbackReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
