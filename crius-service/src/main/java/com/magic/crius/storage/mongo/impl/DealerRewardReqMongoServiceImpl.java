package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.DealerRewardReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.storage.mongo.DealerRewardReqMongoService;
import com.magic.crius.vo.DealerRewardReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:54
 */
@Service
public class DealerRewardReqMongoServiceImpl implements DealerRewardReqMongoService {

    @Resource
    private DealerRewardReqMongoDao dealerRewardReqMongoDao;

    @Override
    public boolean save(DealerRewardReq req) {
        try {
            return dealerRewardReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(DealerRewardReq req) {
        try {
            return dealerRewardReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName("dealerRewardReq")) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public DealerRewardReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return dealerRewardReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
