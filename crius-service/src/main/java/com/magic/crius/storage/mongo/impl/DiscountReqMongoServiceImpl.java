package com.magic.crius.storage.mongo.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.dao.mongo.DiscountReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.DiscountReqMongoService;
import com.magic.crius.vo.DiscountReq;
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
 * Date: 2017/6/3
 * Time: 10:24
 */
@Service
public class DiscountReqMongoServiceImpl implements DiscountReqMongoService {

    @Resource
    private DiscountReqMongoDao discountReqMongoDao;

    @Override
    public boolean save(DiscountReq req) {
        return discountReqMongoDao.save(req, MongoCollectionFlag.dateCollName(MongoCollections.discountReq, req.getPdate())) != null;
    }

    @Override
    public boolean saveFailedData(DiscountReq discountReq) {
        try {
            return discountReqMongoDao.save(discountReq, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.discountReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DiscountReq getByReqId(DiscountReq req) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(req.getReqId()));
            return discountReqMongoDao.findOne(query, MongoCollectionFlag.dateCollName(MongoCollections.discountReq, req.getPdate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return discountReqMongoDao.getSucIds(queryVo, MongoCollectionFlag.dateCollName(MongoCollections.discountReq, queryVo.getPdate()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return discountReqMongoDao.getNotProc(queryVo, MongoCollectionFlag.dateCollName(MongoCollections.discountReq, queryVo.getPdate()), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DiscountReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return discountReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.discountReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<DiscountReq> reqs) {
        try {
            String collName = MongoCollectionFlag.dateCollName(MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.discountReq), reqs.get(0).getPdate());
            return discountReqMongoDao.save(reqs, collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<DiscountReq> getByPage(int page, int count) {
        try {
            if (page < 1) {
                page = 1;
            }
            if (count > 1000) {
                count = 1000;
            }
            Query query = new Query();
            query.skip((page - 1) * count).limit(count);
            return discountReqMongoDao.find(query);
        } catch (Exception e) {
            ApiLogger.error("DiscountReqMongoServiceImpl::getByPage::error", e);
        }
        return null;
    }
}
