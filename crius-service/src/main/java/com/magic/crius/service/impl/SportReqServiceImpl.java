package com.magic.crius.service.impl;

import com.magic.crius.service.SportReqService;
import com.magic.crius.storage.mongo.SportReqMongoService;
import com.magic.crius.storage.redis.SportReqRedisService;
import com.magic.crius.vo.SportReq;
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
public class SportReqServiceImpl implements SportReqService {

    @Resource
    private SportReqMongoService sportReqMongoService;
    @Resource
    private SportReqRedisService sportReqRedisService;

    @Override
    public boolean save(SportReq req) {
        if (!sportReqMongoService.save(req)) {
            sportReqMongoService.saveFailedData(req);
        }
        if (!sportReqRedisService.save(req)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public SportReq getByReqId(Long reqId) {
        return sportReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<SportReq> batchPopRedis(Date date) {
        return sportReqRedisService.batchPop(date);
    }
}
