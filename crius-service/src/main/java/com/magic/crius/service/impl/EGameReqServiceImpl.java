package com.magic.crius.service.impl;

import com.magic.crius.service.EGameReqService;
import com.magic.crius.storage.mongo.EGameReqMongoService;
import com.magic.crius.storage.redis.EGameReqRedisService;
import com.magic.crius.vo.EGameReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:30
 */
@Service
public class EGameReqServiceImpl implements EGameReqService {

    @Resource
    private EGameReqRedisService eGameReqRedisService;
    @Resource
    private EGameReqMongoService eGameReqMongoService;

    @Override
    public boolean save(EGameReq req) {
        if (!eGameReqMongoService.save(req)) {
            eGameReqMongoService.saveFailedData(req);
        }
        if (!eGameReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public boolean saveSuc(Collection<EGameReq> reqs) {
        return false;
    }

    @Override
    public EGameReq getByReqId(Long reqId) {
        return eGameReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<EGameReq> batchPopRedis(Date date) {
        return eGameReqRedisService.batchPop(date);
    }
}
