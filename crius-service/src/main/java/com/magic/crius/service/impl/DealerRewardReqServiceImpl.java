package com.magic.crius.service.impl;

import com.magic.crius.service.DealerRewardReqService;
import com.magic.crius.storage.mongo.DealerRewardReqMongoService;
import com.magic.crius.storage.redis.DealerRewardReqRedisService;
import com.magic.crius.vo.DealerRewardReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:35
 */
@Service
public class DealerRewardReqServiceImpl implements DealerRewardReqService {

    @Resource
    private DealerRewardReqMongoService dealerRewardReqMongoService;
    @Resource
    private DealerRewardReqRedisService dealerRewardReqRedisService;

    @Override
    public boolean save(DealerRewardReq req) {
        if (!dealerRewardReqMongoService.save(req)) {
            dealerRewardReqMongoService.saveFailedData(req);
        }
        if (!dealerRewardReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public DealerRewardReq getByReqId(Long reqId) {
        return dealerRewardReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<DealerRewardReq> batchPopRedis(Date date) {
        return dealerRewardReqRedisService.batchPop(date);
    }

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return dealerRewardReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<DealerRewardReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        return dealerRewardReqMongoService.getNotProc(startTime, endTime, reqIds);
    }

    @Override
    public List<DealerRewardReq> getSaveFailed(Long startTime, Long endTime) {
        return dealerRewardReqMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<DealerRewardReq> reqs) {
        return dealerRewardReqMongoService.saveSuc(reqs);
    }
}
