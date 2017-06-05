package com.magic.crius.service.impl;

import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.storage.mongo.OnlChargeReqMongoService;
import com.magic.crius.storage.redis.OnlChargeReqRedisService;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        if (!onlChargeMongoService.save(onlChargeReq)) {
            onlChargeMongoService.saveFailedData(onlChargeReq);
        }
        if (!onlChargeRedisService.save(onlChargeReq)) {
            //TODO 缓存保存失败如何处理
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
}
