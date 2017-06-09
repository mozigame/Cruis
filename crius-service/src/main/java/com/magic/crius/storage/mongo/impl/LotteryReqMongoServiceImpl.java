package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.LotteryReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.storage.mongo.LotteryReqMongoService;
import com.magic.crius.vo.LotteryReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:30
 */
@Service
public class LotteryReqMongoServiceImpl implements LotteryReqMongoService {

    @Resource
    private LotteryReqMongoDao baseGameReqMongoDao;

    @Override
    public boolean save(LotteryReq req) {
        try {
            return baseGameReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(LotteryReq req) {
        try {
            return baseGameReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("lotteryReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public LotteryReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return baseGameReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
