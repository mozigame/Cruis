package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.EGameReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.storage.mongo.EGameReqMongoService;
import com.magic.crius.vo.EGameReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:08
 */
@Service
public class EGameReqMongoServiceImpl implements EGameReqMongoService {

    @Resource
    private EGameReqMongoDao eGameReqMongoDao;

    @Override
    public boolean save(EGameReq req) {
        try {
            return eGameReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(EGameReq req) {
        try {
            return eGameReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("eGameReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public EGameReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return eGameReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
