package com.magic.crius.service.impl;

import com.magic.crius.dao.mongo.OnlChargeMongoDao;
import com.magic.crius.service.OnlChargeService;
import com.magic.crius.storage.mongo.OnlChargeMongoService;
import com.magic.crius.storage.redis.OnlChargeRedisService;
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
@Service("onlChargeService")
public class OnlChargeServiceImpl implements OnlChargeService {

    @Resource
    private OnlChargeMongoService onlChargeMongoService;
    @Resource
    private OnlChargeRedisService onlChargeRedisService;

    @Override
    public boolean save(OnlChargeReq onlChargeReq) {
        if (!onlChargeMongoService.save(onlChargeReq)) {
            onlChargeMongoService.saveFailedData(onlChargeReq);
        }
        if (!onlChargeMongoService.save(onlChargeReq)) {
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
