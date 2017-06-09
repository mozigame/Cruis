package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.BaseOrderReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.storage.mongo.BaseOrderReqMongoService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:11
 */
@Service
public class BaseOrderReqMongoServiceImpl implements BaseOrderReqMongoService {

    @Resource
    private BaseOrderReqMongoDao vGameReqMongoDao;

    @Override
    public boolean save(BaseOrderReq req) {
        try {
            return vGameReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(BaseOrderReq req) {
        try {
            return vGameReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("baseGameReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BaseOrderReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return vGameReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
