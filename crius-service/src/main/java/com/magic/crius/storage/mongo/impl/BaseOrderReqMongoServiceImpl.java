package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.BaseOrderReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.BaseOrderReqMongoService;
import com.magic.crius.vo.BaseOrderReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;
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
            return baseOrderReqMongoDao.save(req, MongoCollectionFlag.dateCollName(MongoCollections.baseOrderReq, req.getPdate())) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(BaseOrderReq req) {
        try {
            return baseOrderReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.baseOrderReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseOrderReq getByReqId(BaseOrderReq req) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(req.getReqId()));
            return baseOrderReqMongoDao.findOne(query, MongoCollectionFlag.dateCollName(MongoCollections.baseOrderReq, req.getPdate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            String collName = MongoCollectionFlag.dateCollName(MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.baseOrderReq), queryVo.getPdate());
            return baseOrderReqMongoDao.getSucIds(queryVo, collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BaseOrderReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return baseOrderReqMongoDao.getNotProc(queryVo, MongoCollectionFlag.dateCollName(MongoCollections.baseOrderReq, queryVo.getPdate()), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return baseOrderReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.baseOrderReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<BaseOrderReq> reqs) {
        try {
            String collName = MongoCollectionFlag.dateCollName(MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.baseOrderReq), reqs.get(0).getPdate());
            return baseOrderReqMongoDao.save(reqs, collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
