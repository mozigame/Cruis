package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OperateChargeMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.OperateChargeMongoService;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:46
 */
@Service
public class OperateChargeMongoServiceImpl implements OperateChargeMongoService {

    @Resource
    private OperateChargeMongoDao operateChargeMongoDao;

    @Override
    public boolean save(OperateChargeReq operateChargeReq) {
        try {
            return operateChargeMongoDao.save(operateChargeReq) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(OperateChargeReq operateChargeReq) {
        try {
            return operateChargeMongoDao.save(operateChargeReq, FailedFlag.MONGO_FAILED.failedCollName("operateChargeReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OperateChargeReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return operateChargeMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
