package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.SportReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.storage.mongo.SportReqMongoService;
import com.magic.crius.vo.SportReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:06
 */
@Service
public class SportReqMongoServiceImpl implements SportReqMongoService {

    @Resource
    private SportReqMongoDao sportReqMongoDao;

    @Override
    public boolean save(SportReq req) {
        try {
            return sportReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(SportReq req) {
        try {
            return sportReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("sportReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SportReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return sportReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
