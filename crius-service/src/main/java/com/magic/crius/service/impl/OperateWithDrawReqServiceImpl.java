package com.magic.crius.service.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
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
        if (operateWithDrawReqMongoService.save(req)) {
            if (!operateWithDrawReqRedisService.save(req)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save operateWithDrawReq false, retry one time,billId : "+ JSON.toJSONString(req.getBillIds()));
                if (!operateWithDrawReqRedisService.save(req)) {
                    ApiLogger.error("retry save operateWithDrawReq false, billId : "+ JSON.toJSONString(req.getBillIds()));
                    FailedRedisQueue.operateWithDrawQueue.add(req);
                }
            }
        } else {
            operateWithDrawReqMongoService.saveFailedData(req);
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
