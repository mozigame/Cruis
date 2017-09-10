package com.magic.crius.service.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.storage.mongo.PreCmpChargeReqMongoService;
import com.magic.crius.storage.redis.PreCmpChargeReqRedisService;
import com.magic.crius.vo.PreCmpChargeReq;
import com.magic.crius.vo.ReqQueryVo;
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
        if (preCmpChargeMongoService.save(preCmpChargeReq)) {
            if (!preCmpChargeRedisService.save(preCmpChargeReq)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save preCmpChargeReq false, retry one time,billId : "+ preCmpChargeReq.getBillId());
                if (!preCmpChargeRedisService.save(preCmpChargeReq)) {
                    ApiLogger.error("retry save preCmpChargeReq false, billId : "+ preCmpChargeReq.getBillId());
                    FailedRedisQueue.preCmpChargeQueue.add(preCmpChargeReq);
                }
            }
        } else {
            preCmpChargeMongoService.saveFailedData(preCmpChargeReq);
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
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        return preCmpChargeMongoService.getSucIds(queryVo);
    }

    @Override
    public List<PreCmpChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        return preCmpChargeMongoService.getNotProc(queryVo, pageable);
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
