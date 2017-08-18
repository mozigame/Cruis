package com.magic.crius.service.impl;

import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.storage.mongo.OperateChargeReqMongoService;
import com.magic.crius.storage.redis.OperateChargeReqRedisService;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
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
        if (operateChargeMongoService.save(operateChargeReq)) {
            if (!operateChargeRedisService.save(operateChargeReq)) {
                //TODO 缓存保存失败如何处理
            }
        } else {
            operateChargeMongoService.saveFailedData(operateChargeReq);
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

    @Override
    public List<Long> getSucIds(Long startTime, Long endTime) {
        return operateChargeMongoService.getSucIds(startTime, endTime);
    }

    @Override
    public List<OperateChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable) {
        return operateChargeMongoService.getNotProc(startTime,endTime,reqIds, pageable);
    }

    @Override
    public List<OperateChargeReq> getSaveFailed(Long startTime, Long endTime) {
        return operateChargeMongoService.getSaveFailed(startTime,endTime);
    }

    @Override
    public boolean saveSuc(List<OperateChargeReq> reqs) {
        return operateChargeMongoService.saveSuc(reqs);
    }


}
