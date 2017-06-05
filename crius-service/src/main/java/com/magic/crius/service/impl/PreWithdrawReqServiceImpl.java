package com.magic.crius.service.impl;

import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.storage.mongo.PreWithdrawReqMongoService;
import com.magic.crius.storage.redis.PreWithdrawReqRedisService;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:22
 */
@Service
public class PreWithdrawReqServiceImpl implements PreWithdrawReqService {

    @Resource
    private PreWithdrawReqMongoService preWithdrawMongoService;
    @Resource
    private PreWithdrawReqRedisService preWithdrawRedisService;

    @Override
    public boolean save(PreWithdrawReq preWithdrawReq) {
        if (!preWithdrawMongoService.save(preWithdrawReq)) {
            preWithdrawMongoService.saveFailedData(preWithdrawReq);
        }
        if (!preWithdrawRedisService.save(preWithdrawReq)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public PreWithdrawReq getByReqId(Long reqId) {
        return preWithdrawMongoService.getByReqId(reqId);
    }

    @Override
    public List<PreWithdrawReq> batchPopRedis(Date date) {
        return preWithdrawRedisService.batchPop(date);
    }


}
