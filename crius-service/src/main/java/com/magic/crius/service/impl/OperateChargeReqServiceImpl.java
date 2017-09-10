package com.magic.crius.service.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.storage.mongo.OperateChargeReqMongoService;
import com.magic.crius.storage.redis.OperateChargeReqRedisService;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.ReqQueryVo;
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
                //TODO 缓存保存失败如何处理，睡眠2毫秒，然后重试，如果失败，扔进队列中
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ApiLogger.error("save operateChargeReq false, retry one time,billId : "+ JSON.toJSONString(operateChargeReq.getBillIds()));
                if (!operateChargeRedisService.save(operateChargeReq)) {
                    ApiLogger.error("retry save operateChargeReq false, billId : "+ JSON.toJSONString(operateChargeReq.getBillIds()));
                    FailedRedisQueue.operateChargeQueue.add(operateChargeReq);
                }
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
    public List<Long> getSucIds(ReqQueryVo queryVo) {
        return operateChargeMongoService.getSucIds(queryVo);
    }

    @Override
    public List<OperateChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable) {
        return operateChargeMongoService.getNotProc(queryVo, pageable);
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
