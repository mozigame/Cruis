package com.magic.crius.service.impl;

import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.storage.mongo.BaseOrderReqMongoService;
import com.magic.crius.storage.redis.BaseOrderReqRedisService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:25
 */
@Service
public class BaseOrderReqServiceImpl implements BaseOrderReqService {

    @Resource
    private BaseOrderReqRedisService baseOrderReqRedisService;
    @Resource
    private BaseOrderReqMongoService baseOrderReqMongoService;

    @Override
    public boolean save(BaseOrderReq req) {
        if (!baseOrderReqMongoService.save(req)) {
            baseOrderReqMongoService.saveFailedData(req);
        }
        if (!baseOrderReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public BaseOrderReq getByReqId(Long reqId) {
        return baseOrderReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<BaseOrderReq> batchPopRedis(Date date) {
        return baseOrderReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return baseOrderReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<BaseOrderReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        return baseOrderReqMongoService.getNotProc(startTime, endTime, reqIds);
    }

    @Override
    public List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime) {
        return baseOrderReqMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(Collection<BaseOrderReq> reqs) {
        return baseOrderReqMongoService.saveSuc(reqs);
    }
}
