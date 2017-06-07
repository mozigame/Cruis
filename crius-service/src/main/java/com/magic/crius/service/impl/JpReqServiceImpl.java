package com.magic.crius.service.impl;

import com.magic.crius.service.JpReqService;
import com.magic.crius.storage.mongo.JpReqMongoService;
import com.magic.crius.storage.redis.JpReqRedisService;
import com.magic.crius.vo.JpReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:45
 */
@Service
public class JpReqServiceImpl implements JpReqService {

    @Resource
    private JpReqMongoService jpReqMongoService;

    private JpReqRedisService jpReqRedisService;

    @Override
    public boolean save(JpReq req) {
        if (!jpReqMongoService.save(req)) {
            jpReqMongoService.saveFailedData(req);
            //todo
        }
        if (!jpReqRedisService.save(req)) {

        }
        return true;
    }

    @Override
    public JpReq getByReqId(Long reqId) {
        return jpReqMongoService.getByReqId(reqId);
    }

    @Override
    public List<JpReq> batchPopRedis(Date date) {
        return jpReqRedisService.batchPop(date);
    }
}
