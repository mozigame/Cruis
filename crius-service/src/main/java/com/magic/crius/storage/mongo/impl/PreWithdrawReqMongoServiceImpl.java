package com.magic.crius.storage.mongo.impl;

import com.magic.crius.dao.mongo.PreWithdrawReqMongoDao;
import com.magic.crius.enums.MongoCollectionFlag;
import com.magic.crius.enums.MongoCollections;
import com.magic.crius.storage.mongo.PreWithdrawReqMongoService;
import com.magic.crius.vo.PreWithdrawReq;
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
 * Time: 19:14
 */
@Service
public class PreWithdrawReqMongoServiceImpl implements PreWithdrawReqMongoService {

    @Resource
    private PreWithdrawReqMongoDao preWithdrawMongoDao;

    @Override
    public boolean save(PreWithdrawReq preWithdrawReq) {
        try {
            return preWithdrawMongoDao.save(preWithdrawReq) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveFailedData(PreWithdrawReq preWithdrawReq) {
        try {
            return preWithdrawMongoDao.save(preWithdrawReq, MongoCollectionFlag.MONGO_FAILED.collName(MongoCollections.preWithdrawReq)) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PreWithdrawReq getByReqId(Long id) {
        try {
            Query query = new Query();
            query.addCriteria(new Criteria("reqId").is(id));
            return preWithdrawMongoDao.findOne(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean saveSuc(Collection<PreWithdrawReq> reqs) {
        try {
            return preWithdrawMongoDao.save(reqs, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.preWithdrawReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        try {
            return preWithdrawMongoDao.getSucIds(queryVo, MongoCollectionFlag.SAVE_SUC.collName(MongoCollections.preWithdrawReq));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PreWithdrawReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        try {
            return preWithdrawMongoDao.getNotProc(queryVo, MongoCollections.preWithdrawReq, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PreWithdrawReq> getSaveFailed(Long startTime, Long endTime) {
        try {
            return preWithdrawMongoDao.getSaveFailed(startTime, endTime, MongoCollections.preWithdrawReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
