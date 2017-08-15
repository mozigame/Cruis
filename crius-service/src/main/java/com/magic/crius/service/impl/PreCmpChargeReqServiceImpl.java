package com.magic.crius.service.impl;

import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.storage.mongo.PreCmpChargeReqMongoService;
import com.magic.crius.storage.redis.PreCmpChargeReqRedisService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:37
 */
@Service
public class PreCmpChargeReqServiceImpl implements PreCmpChargeReqService {

    @Resource
    private PreCmpChargeReqMongoService preCmpChargeMongoService;

    @Resource
    private PreCmpChargeReqRedisService preCmpChargeRedisService;


    @Override
    public boolean savePreCmpCharge(PreCmpChargeReq preCmpChargeReq) {
        if (!preCmpChargeMongoService.save(preCmpChargeReq)) {
            preCmpChargeMongoService.saveFailedData(preCmpChargeReq);
        }
        if (!preCmpChargeRedisService.save(preCmpChargeReq)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public PreCmpChargeReq getByReqId(Long reqId) {
        return preCmpChargeMongoService.getByReqId(reqId);
    }

    @Override
    public List<PreCmpChargeReq> batchPopRedis(Date date) {
        return preCmpChargeRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return preCmpChargeMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<PreCmpChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return preCmpChargeMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<PreCmpChargeReq> getSaveFailed(Long startTime, Long endTime) {
        return preCmpChargeMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<PreCmpChargeReq> reqs) {
        return preCmpChargeMongoService.saveSuc(reqs);
    }
}
