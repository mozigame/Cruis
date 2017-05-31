package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.PreCmpChargeMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.PreCmpChargeMongoService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
@Service("preCmpChargeMongoService")
public class PreCmpChargeMongoServiceImpl implements PreCmpChargeMongoService {

    @Resource
    private PreCmpChargeMongoDao preCmpChargeMongoDao;


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
            return preCmpChargeMongoDao.save(preCmpChargeReq, FailedFlag.MONGO_FAILED.failedCollName("preCmpChargeReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PreCmpChargeReq getByReqId(Long reqId) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("ReqId").is(String.valueOf(reqId)));
            return preCmpChargeMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
