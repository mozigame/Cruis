package com.magic.crius.service.impl;

import com.magic.crius.service.DiscountReqService;
import com.magic.crius.storage.mongo.DiscountReqMongoService;
import com.magic.crius.storage.redis.DiscountReqRedisService;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        if (!discountReqMongoService.save(discountReq)) {
            discountReqMongoService.saveFailedData(discountReq);
        }
        if (!discountReqRedisService.save(discountReq)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public DiscountReq getByReqId(Long reqId) {
        return discountReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<DiscountReq> batchPopRedis(Date date) {
        return discountReqRedisService.batchPop(date);
    }
}
