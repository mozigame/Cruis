package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.PreWithdrawReqMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.PreWithdrawReqMongoService;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:14
 */
@Service
public class PreWithdrawReqMongoServiceImpl implements PreWithdrawReqMongoService {

    @Resource
    private PreWithdrawReqMongoDao preWithdrawMongoDao;

    @Override
    public boolean save(PreWithdrawReq preWithdrawReq) {
        try {
            return preWithdrawMongoDao.save(preWithdrawReq) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(PreWithdrawReq preWithdrawReq) {
        try {
            return preWithdrawMongoDao.save(preWithdrawReq, FailedFlag.MONGO_FAILED.failedCollName("preWithdrawReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PreWithdrawReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return preWithdrawMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
