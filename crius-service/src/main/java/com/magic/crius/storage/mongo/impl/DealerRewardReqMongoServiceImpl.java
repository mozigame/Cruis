package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.DealerRewardReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.DealerRewardReqMongoService;
import com.magic.crius.vo.DealerRewardReq;
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
 * Time: 20:54
 */
@Service
public class DealerRewardReqMongoServiceImpl implements DealerRewardReqMongoService {

    @Resource
    private DealerRewardReqMongoDao dealerRewardReqMongoDao;

    @Override
    public boolean save(DealerRewardReq req) {
        try {
            return dealerRewardReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(DealerRewardReq req) {
        try {
            return dealerRewardReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.dealerRewardReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DealerRewardReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return dealerRewardReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return dealerRewardReqMongoDao.getSucIds(queryVo, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.dealerRewardReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DealerRewardReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return dealerRewardReqMongoDao.getNotProc(queryVo, MongoCollections.dealerRewardReq, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DealerRewardReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return dealerRewardReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.dealerRewardReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<DealerRewardReq> reqs) {
        try {
            return dealerRewardReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.dealerRewardReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
