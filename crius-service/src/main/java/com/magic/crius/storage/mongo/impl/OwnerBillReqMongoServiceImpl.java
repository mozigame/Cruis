package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OwnerBillReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.OwnerBillReqMongoService;
import com.magic.crius.vo.OwnerBillReq;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: justin
 * Date: 2017/7/15
 */
@Service
public class OwnerBillReqMongoServiceImpl implements OwnerBillReqMongoService {

    @Resource
    private OwnerBillReqMongoDao ownerBillReqMongoDao;

    @Override
    public boolean save(OwnerBillReq req) {
        try {
            return ownerBillReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(OwnerBillReq req) {
        try {
            return ownerBillReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.ownerBillReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OwnerBillReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return ownerBillReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean saveSuc(Collection<OwnerBillReq> reqs) {
        try {
            return ownerBillReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.ownerBillReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return ownerBillReqMongoDao.getSucIds(startTime, endTime, MongoCollections.ownerBillReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OwnerBillReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return ownerBillReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.ownerBillReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
