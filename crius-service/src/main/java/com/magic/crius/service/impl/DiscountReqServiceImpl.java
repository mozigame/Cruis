package com.magic.crius.service.impl;

import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.storage.mongo.DiscountReqMongoService;
import com.magic.crius.storage.redis.DiscountReqRedisService;
import com.magic.crius.vo.DiscountReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:09
 */
@Service
public class DiscountReqServiceImpl implements DiscountReqService {

    @Resource
    private DiscountReqRedisService discountReqRedisService;
    @Resource
    private DiscountReqMongoService discountReqMongoService;

    @Override
    public boolean save(DiscountReq discountReq) {
        if (discountReqMongoService.save(discountReq)) {
            if (!discountReqRedisService.save(discountReq)) {
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save discountReq false, retry one time,billId : "+ discountReq.getBillId());
                if (!discountReqRedisService.save(discountReq)) {
                    ApiLogger.error("retry save discountReq false, billId : "+ discountReq.getBillId());
                    FailedRedisQueue.discountQueue.add(discountReq);
                }
            }
        } else {
            discountReqMongoService.saveFailedData(discountReq);
        }
        return true;
    }

    @Override
    public DiscountReq getByReqId(DiscountReq req) {
        return discountReqMongoService.getByReqId(req);
    }

    @Override
    public List<DiscountReq> batchPopRedis(Date date) {
        return discountReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        return discountReqMongoService.getSucIds(queryVo);
    }

    @Override
    public List<DiscountReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        return discountReqMongoService.getNotProc(queryVo, pageable);
    }

    @Override
    public List<DiscountReq> getSaveFailed(Long startTime, Long endTime) {
        return discountReqMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<DiscountReq> reqs) {
        return discountReqMongoService.saveSuc(reqs);
    }
}
