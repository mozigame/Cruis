package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.CashbackReqMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.CashbackReqMongoService;
import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DiscountReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        return cashbackReqMongoDao.save(req) != null;
    }

    @Override
    public boolean saveFailedData(CashbackReq req) {
        try {
            return cashbackReqMongoDao.save(req, FailedFlag.MONGO_FAILED.failedCollName("cashbackReq")) != null;
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
}
