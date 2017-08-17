package com.magic.crius.service.impl;

import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.storage.mongo.OnlChargeReqMongoService;
import com.magic.crius.storage.redis.OnlChargeReqRedisService;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:12
 */
@Service
public class OnlChargeReqServiceImpl implements OnlChargeReqService {

    @Resource
    private OnlChargeReqMongoService onlChargeMongoService;
    @Resource
    private OnlChargeReqRedisService onlChargeRedisService;

    @Override
    public boolean save(OnlChargeReq onlChargeReq) {
        if (onlChargeMongoService.save(onlChargeReq)) {
            if (!onlChargeRedisService.save(onlChargeReq)) {
                //TODO 缓存保存失败如何处理
            }
        } else {
            onlChargeMongoService.saveFailedData(onlChargeReq);
        }

        return true;
    }

    @Override
    public OnlChargeReq getByReqId(Long reqId) {
        return onlChargeMongoService.getByReqId(reqId);
    }

    @Override
    public List<OnlChargeReq> batchPopRedis(Date date) {
        return onlChargeRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return onlChargeMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<OnlChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return onlChargeMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<OnlChargeReq> getSaveFailed(Long startTime, Long endTime) {
        return onlChargeMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<OnlChargeReq> reqs) {
        return onlChargeMongoService.saveSuc(reqs);
    }
}
