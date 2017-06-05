package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OnlChargeReqMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.OnlChargeReqMongoService;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
            return onlChargeMongoDao.save(onlChargeReq, FailedFlag.MONGO_FAILED.failedCollName("onlChargeReq")) != null;
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
}
