package com.magic.crius.service.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.CashbackReqService;
import com.magic.crius.storage.mongo.CashbackReqMongoService;
import com.magic.crius.storage.redis.CashbackReqRedisService;
import com.magic.crius.vo.CashbackReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:45
 */
@Service
public class CashbackReqServiceImpl implements CashbackReqService {

    @Resource
    private CashbackReqMongoService cashbackReqMongoService;
    @Resource
    private CashbackReqRedisService cashbackReqRedisService;


    @Override
    public boolean save(CashbackReq req) {
        if (cashbackReqMongoService.save(req)) {
            if (!cashbackReqRedisService.save(req)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.warn("save cashbackReq false, retry one time,billId : "+ req.getBillId());
                if (!cashbackReqRedisService.save(req)) {
                    FailedRedisQueue.cashbackQueue.add(req);
                }
            }
        } else {
            cashbackReqMongoService.saveFailedData(req);
        }
        return true;
    }

    @Override
    public boolean saveSuc(Collection<CashbackReq> reqs) {
        return cashbackReqMongoService.saveSuc(reqs);
    }

    @Override
    public CashbackReq getByReqId(Long reqId) {
        return cashbackReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<CashbackReq> batchPopRedis(Date date) {
        return cashbackReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return cashbackReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<CashbackReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return cashbackReqMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<CashbackReq> getSaveFailed(Long startTime, Long endTime) {
        return cashbackReqMongoService.getSaveFailed(startTime, endTime);
    }

}
