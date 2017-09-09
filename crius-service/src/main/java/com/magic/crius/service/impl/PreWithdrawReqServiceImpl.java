package com.magic.crius.service.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.storage.mongo.PreWithdrawReqMongoService;
import com.magic.crius.storage.redis.PreWithdrawReqRedisService;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:22
 */
@Service
public class PreWithdrawReqServiceImpl implements PreWithdrawReqService {

    @Resource
    private PreWithdrawReqMongoService preWithdrawMongoService;
    @Resource
    private PreWithdrawReqRedisService preWithdrawRedisService;

    @Override
    public boolean save(PreWithdrawReq preWithdrawReq) {
        if (preWithdrawMongoService.save(preWithdrawReq)) {
            if (!preWithdrawRedisService.save(preWithdrawReq)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save preWithDrawReq false, retry one time,billId : "+ preWithdrawReq.getBillId());
                if (!preWithdrawRedisService.save(preWithdrawReq)) {
                    ApiLogger.error("retry save preWithDrawReq false, billId : "+ preWithdrawReq.getBillId());
                    FailedRedisQueue.preWithdrawQueue.add(preWithdrawReq);
                }
            }
        } else {
            preWithdrawMongoService.saveFailedData(preWithdrawReq);
        }
        return true;
    }

    @Override
    public PreWithdrawReq getByReqId(Long reqId) {
        return preWithdrawMongoService.getByReqId(reqId);
    }

    @Override
    public List<PreWithdrawReq> batchPopRedis(Date date) {
        return preWithdrawRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return preWithdrawMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<PreWithdrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return preWithdrawMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<PreWithdrawReq> getSaveFailed(Long startTime, Long endTime) {
        return preWithdrawMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<PreWithdrawReq> reqs) {
        return preWithdrawMongoService.saveSuc(reqs);
    }

}
