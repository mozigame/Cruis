package com.magic.crius.service.impl;

import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.storage.mongo.BaseOrderReqMongoService;
import com.magic.crius.storage.redis.BaseOrderReqRedisService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:25
 */
@Service
public class BaseOrderReqServiceImpl implements BaseOrderReqService {

    @Resource
    private BaseOrderReqRedisService vGameReqRedisService;
    @Resource
    private BaseOrderReqMongoService vGameReqMongoService;

    @Override
    public boolean save(BaseOrderReq req) {
        if (!vGameReqMongoService.save(req)) {
            vGameReqMongoService.saveFailedData(req);
        }
        if (!vGameReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public BaseOrderReq getByReqId(Long reqId) {
        return vGameReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<BaseOrderReq> batchPopRedis(Date date) {
        return vGameReqRedisService.batchPop(date);
    }
}
