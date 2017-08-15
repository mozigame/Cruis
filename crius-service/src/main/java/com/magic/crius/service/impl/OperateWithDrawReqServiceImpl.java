package com.magic.crius.service.impl;

import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.storage.mongo.OperateWithDrawReqMongoService;
import com.magic.crius.storage.redis.OperateWithDrawReqRedisService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:46
 */
@Service
public class OperateWithDrawReqServiceImpl implements OperateWithDrawReqService {

    private static final Logger logger = Logger.getLogger(OperateWithDrawReqServiceImpl.class);

    @Resource
    private OperateWithDrawReqRedisService operateWithDrawReqRedisService;
    @Resource
    private OperateWithDrawReqMongoService operateWithDrawReqMongoService;

    @Override
    public boolean save(OperateWithDrawReq req) {
        if (!operateWithDrawReqMongoService.save(req)) {
            operateWithDrawReqMongoService.saveFailedData(req);
        }
        if (!operateWithDrawReqRedisService.save(req)) {
            logger.warn("operateWithDrawReq insert redis failed,reqId : " + req.getReqId());
        }
        return true;
    }

    @Override
    public boolean saveSuc(List<OperateWithDrawReq> reqs) {
        return operateWithDrawReqMongoService.saveSuc(reqs);
    }

    @Override
    public OperateWithDrawReq getByReqId(Long reqId) {
        return operateWithDrawReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<OperateWithDrawReq> batchPopRedis(Date date) {
        return operateWithDrawReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return operateWithDrawReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<OperateWithDrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return operateWithDrawReqMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<OperateWithDrawReq> getSaveFailed(Long startTime, Long endTime) {
        return operateWithDrawReqMongoService.getSaveFailed(startTime, endTime);
    }
}
