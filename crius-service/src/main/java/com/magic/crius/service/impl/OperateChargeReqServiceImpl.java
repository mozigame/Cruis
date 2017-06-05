package com.magic.crius.service.impl;

import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.storage.mongo.OperateChargeReqMongoService;
import com.magic.crius.storage.redis.OperateChargeReqRedisService;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:41
 */
@Service
public class OperateChargeReqServiceImpl implements OperateChargeReqService {


    @Resource
    private OperateChargeReqMongoService operateChargeMongoService;
    @Resource
    private OperateChargeReqRedisService operateChargeRedisService;

    @Override
    public boolean save(OperateChargeReq operateChargeReq) {
        if (!operateChargeMongoService.save(operateChargeReq)) {
            operateChargeMongoService.saveFailedData(operateChargeReq);
        }
        if (!operateChargeRedisService.save(operateChargeReq)) {
            //TODO 缓存保存失败如何处理
        }
        return true;
    }

    @Override
    public OperateChargeReq getByReqId(Long reqId) {
        return operateChargeMongoService.getByReqId(reqId);
    }

    @Override
    public List<OperateChargeReq> batchPopRedis(Date date) {
        return operateChargeRedisService.batchPop(date);
    }


}
