package com.magic.crius.storage.mongo.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.magic.crius.dao.mongo.AgentBillReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.AgentBillReqMongoService;
import com.magic.crius.vo.AgentBillReq;

/**
 * User: justin
 * Date: 2017/7/15
 */
@Service
public class AgentBillReqMongoServiceImpl implements AgentBillReqMongoService {

    @Resource
    private AgentBillReqMongoDao agentBillReqMongoDao;

    @Override
    public boolean save(AgentBillReq req) {
        try {
            return agentBillReqMongoDao.save(req) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(AgentBillReq req) {
        try {
            return agentBillReqMongoDao.save(req, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.agentBillReq.name())) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public AgentBillReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return agentBillReqMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean saveSuc(Collection<AgentBillReq> reqs) {
        try {
            return agentBillReqMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.agentBillReq.name()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        try {
            return agentBillReqMongoDao.getSucIds(startTime, endTime, MongoCollections.agentBillReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AgentBillReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        try {
            agentBillReqMongoDao.getNotProc(startTime,endTime,reqIds, MongoCollections.agentBillReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AgentBillReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            agentBillReqMongoDao.getSaveFailed(startTime, endTime, MongoCollections.agentBillReq.name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
