package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.RepairLockMongoDao;
import com.magic.crius.po.RepairLock;
import com.magic.crius.storage.mongo.RepairLockMongoService;
import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 11:50
 */
@Service
public class RepairLockMongoServiceImpl implements RepairLockMongoService {


    @Resource
    private RepairLockMongoDao repairLockMongoDao;

    @Override
    public boolean save(RepairLock lock) {
        try {
            return repairLockMongoDao.save(lock) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public RepairLock getTimeLock(String collectionName, Integer time) {
        try {
            Query query  = new Query();
            query.addCriteria(new Criteria("collectionName").is(collectionName));
            query.addCriteria(new Criteria("time").is(time));
            return repairLockMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delTimeLock(Long startTime, Long endTime) {
        try {
            Query query  = new Query();
            query.addCriteria(new Criteria("produceTime").gte(startTime));
            query.addCriteria(new Criteria("produceTime").lt(endTime));
            WriteResult result = repairLockMongoDao.getMongoTemplate().remove(query, RepairLock.class);
            return result.getN() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
