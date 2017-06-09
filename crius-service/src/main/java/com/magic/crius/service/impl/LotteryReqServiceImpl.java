package com.magic.crius.service.impl;

import com.magic.crius.service.LotteryReqService;
import com.magic.crius.storage.mongo.LotteryReqMongoService;
import com.magic.crius.storage.redis.LotteryReqRedisService;
import com.magic.crius.vo.LotteryReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:29
 */
@Service
public class LotteryReqServiceImpl implements LotteryReqService {


    @Resource
    private LotteryReqMongoService lotteryReqMongoService;
    @Resource
    private LotteryReqRedisService lotteryReqRedisService;


    @Override
    public boolean save(LotteryReq req) {
        if (!lotteryReqMongoService.save(req)) {
            lotteryReqMongoService.saveFailedData(req);
        }
        if (!lotteryReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public LotteryReq getByReqId(Long reqId) {
        return lotteryReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<LotteryReq> batchPopRedis(Date date) {
        return lotteryReqRedisService.batchPop(date);
    }
}
