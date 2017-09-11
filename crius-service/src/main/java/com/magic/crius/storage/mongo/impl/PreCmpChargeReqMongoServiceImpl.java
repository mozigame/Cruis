package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.PreCmpChargeReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.PreCmpChargeReqMongoService;
import com.magic.crius.vo.PreCmpChargeReq;
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
 * Date: 2017/5/30
 * Time: 14:33
 */
@Service("preCmpChargeMongoService")
public class PreCmpChargeReqMongoServiceImpl implements PreCmpChargeReqMongoService {

    @Resource
    private PreCmpChargeReqMongoDao preCmpChargeMongoDao;


    /**
     * {@inheritDoc}
     *
     * @param preCmpChargeReq
     * @return
     */
    @Override
    public boolean save(PreCmpChargeReq preCmpChargeReq) {
        try {
            return preCmpChargeMongoDao.save(preCmpChargeReq) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(PreCmpChargeReq preCmpChargeReq) {
        try {
            return preCmpChargeMongoDao.save(preCmpChargeReq, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.preCmpChargeReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PreCmpChargeReq getByReqId(Long reqId) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(reqId));
            return preCmpChargeMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(Collection<PreCmpChargeReq> reqs) {
        try {
            return preCmpChargeMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.preCmpChargeReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return preCmpChargeMongoDao.getSucIds(queryVo, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.preCmpChargeReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PreCmpChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
            try {
            return preCmpChargeMongoDao.getNotProc(queryVo,MongoCollections.preCmpChargeReq, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PreCmpChargeReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return preCmpChargeMongoDao.getSaveFailed(startTime,endTime,MongoCollections.preCmpChargeReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
