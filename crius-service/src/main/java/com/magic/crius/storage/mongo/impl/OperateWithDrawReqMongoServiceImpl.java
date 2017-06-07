package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OperateWithDrawReqMongoDao;
import com.magic.crius.enums.FailedFlag;
import com.magic.crius.storage.mongo.OperateWithDrawReqMongoService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:39
 */
@Service
public class OperateWithDrawReqMongoServiceImpl implements OperateWithDrawReqMongoService {

    @Resource
    private OperateWithDrawReqMongoDao operateWithDrawReqMongoDao;

    @Override
    public boolean save(OperateWithDrawReq req) {
        try {
            return operateWithDrawReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(OperateWithDrawReq req) {
        try {
            return operateWithDrawReqMongoDao.save(req, FailedFlag.MONGO_FAILED.failedCollName("operateWithDrawReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OperateWithDrawReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return operateWithDrawReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
