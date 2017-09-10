package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OnlChargeReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.OnlChargeReqMongoService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;
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
            return onlChargeMongoDao.save(onlChargeReq, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.onlChargeReq)) != null;
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
            return onlChargeMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.onlChargeReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return onlChargeMongoDao.getSucIds(queryVo, MongoCollections.onlChargeReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return onlChargeMongoDao.getNotProc(queryVo, MongoCollections.onlChargeReq, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OnlChargeReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return onlChargeMongoDao.getSaveFailed(startTime, endTime, MongoCollections.onlChargeReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
