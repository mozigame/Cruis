package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OnlChargeReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.OnlChargeReqMongoService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:15
 */
@Service
public class OnlChargeReqMongoServiceImpl implements OnlChargeReqMongoService {

    @Resource
    private OnlChargeReqMongoDao onlChargeMongoDao;

    @Override
    public boolean save(OnlChargeReq onlChargeReq) {
        try {
            return onlChargeMongoDao.save(onlChargeReq) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(OnlChargeReq onlChargeReq) {
        try {
            return onlChargeMongoDao.save(onlChargeReq, MongoCollectionFlag.MONGO_FAILED.collName("onlChargeReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OnlChargeReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return onlChargeMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(Collection<OnlChargeReq> reqs) {
        try {
            return onlChargeMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.onlChargeReq.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return onlChargeMongoDao.getSucIds(startTime, endTime, MongoCollections.onlChargeReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        try {
            return onlChargeMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.onlChargeReq.name(), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlChargeReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return onlChargeMongoDao.getSaveFailed(startTime, endTime, MongoCollections.onlChargeReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
