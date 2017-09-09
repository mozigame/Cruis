package com.magic.crius.service.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.JpReqService;
import com.magic.crius.storage.mongo.JpReqMongoService;
import com.magic.crius.storage.redis.JpReqRedisService;
import com.magic.crius.vo.JpReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:45
 */
@Service
public class JpReqServiceImpl implements JpReqService {

    @Resource
    private JpReqMongoService jpReqMongoService;
    @Resource
    private JpReqRedisService jpReqRedisService;

    @Override
    public boolean save(JpReq req) {
        if (jpReqMongoService.save(req)) {
            if (!jpReqRedisService.save(req)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save jpReq false, retry one time,billId : "+ req.getBillId());
                if (!jpReqRedisService.save(req)) {
                    ApiLogger.error("retry save jpReq false, billId : "+ req.getBillId());
                    FailedRedisQueue.jpQueue.add(req);
                }
            }
        } else {
            jpReqMongoService.saveFailedData(req);
        }
        return true;
    }

    @Override
    public JpReq getByReqId(Long reqId) {
        return jpReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<JpReq> batchPopRedis(Date date) {
        return jpReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return jpReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<JpReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return jpReqMongoService.getNotProc(startTime, endTime, reqIds, pageable);
    }

    @Override
    public List<JpReq> getSaveFailed(Long startTime, Long endTime) {
        return jpReqMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<JpReq> reqs) {
        return jpReqMongoService.saveSuc(reqs);
    }
}
