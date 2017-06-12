package com.magic.crius.service.impl;

import com.magic.crius.service.JpReqService;
import com.magic.crius.storage.mongo.JpReqMongoService;
import com.magic.crius.storage.redis.JpReqRedisService;
import com.magic.crius.vo.JpReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
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
    @Resource
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

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return jpReqMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<JpReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds) {
        return jpReqMongoService.getNotProc(startTime, endTime, reqIds);
    }

    @Override
    public List<JpReq> getSaveFailed(Long startTime, Long endTime) {
        return jpReqMongoService.getSaveFailed(startTime, endTime);
    }

    @Override
    public boolean saveSuc(List<JpReq> reqs) {
        return jpReqMongoService.saveSuc(reqs);
    }
}
