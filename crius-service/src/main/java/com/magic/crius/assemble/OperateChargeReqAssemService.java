package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.vo.OperateChargeReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:47
 * 人工充值
 */
@Service
public class OperateChargeReqAssemService  {


    private static final Logger logger = Logger.getLogger(OperateChargeReqAssemService.class);

    @Resource
    private OperateChargeReqService operateChargeService;

    public void procKafkaData(OperateChargeReq req) {
        if (req.getReqId() != null && operateChargeService.getByReqId(req.getReqId()) == null) {
            if (!operateChargeService.save(req)) {
                logger.error("save OperateChargeReq error,reqId : " + req.getReqId());
            }
        } else {
            logger.warn("reqId is null,"+ JSON.toJSONString(req));
        }
    }

}
