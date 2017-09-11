package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.OperateChargeReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.OperateChargeReqMongoService;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:46
 */
@Service
public class OperateChargeReqMongoServiceImpl implements OperateChargeReqMongoService {

    @Resource
    private OperateChargeReqMongoDao operateChargeMongoDao;

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
            return operateChargeMongoDao.save(operateChargeReq, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.operateChargeReq)) != null;
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

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return operateChargeMongoDao.getSucIds(queryVo, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.operateChargeReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OperateChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return operateChargeMongoDao.getNotProc(queryVo, MongoCollections.operateChargeReq, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OperateChargeReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return operateChargeMongoDao.getSaveFailed(startTime, endTime, MongoCollections.operateChargeReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveSuc(List<OperateChargeReq> reqs) {
        try {
            return operateChargeMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.operateChargeReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
