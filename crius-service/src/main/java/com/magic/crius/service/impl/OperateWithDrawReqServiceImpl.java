package com.magic.crius.service.impl;

import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.storage.mongo.OperateWithDrawReqMongoService;
import com.magic.crius.storage.redis.OperateWithDrawReqRedisService;
import com.magic.crius.vo.OperateWithDrawReq;
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
            //TODO 缓存保存失败如何处理
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
    public List<OperateWithDrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        return operateWithDrawReqMongoService.getNotProc(startTime, endTime, reqIds);
    }

    @Override
    public List<OperateWithDrawReq> getSaveFailed(Long startTime, Long endTime) {
        return operateWithDrawReqMongoService.getSaveFailed(startTime, endTime);
    }
}
