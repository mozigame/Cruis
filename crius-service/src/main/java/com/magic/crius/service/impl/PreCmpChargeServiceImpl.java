package com.magic.crius.service.impl;

import com.magic.crius.service.PreCmpChargeService;
import com.magic.crius.storage.mongo.PreCmpChargeMongoService;
import com.magic.crius.storage.redis.PreCmpChargeRedisService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:37
 */
@Service("preCmpChargeService")
public class PreCmpChargeServiceImpl implements PreCmpChargeService{

    @Resource
    private PreCmpChargeMongoService preCmpChargeMongoService;

    @Resource
    private PreCmpChargeRedisService preCmpChargeRedisService;


    @Override
    public boolean savePreCmpCharge(PreCmpChargeReq preCmpChargeReq) {
        if (!preCmpChargeMongoService.save(preCmpChargeReq)) {
            preCmpChargeMongoService.saveFailedData(preCmpChargeReq);
        }
        if (!preCmpChargeRedisService.save(preCmpChargeReq)) {
            //TODO 缓存保存失败如何处理
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
}
