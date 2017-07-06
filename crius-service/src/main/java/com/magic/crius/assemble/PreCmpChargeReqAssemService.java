package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.vo.PreCmpChargeReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:32
 * 公司入款
 */
@Service
public class PreCmpChargeReqAssemService {

    private static final Logger logger = Logger.getLogger(PreCmpChargeReqAssemService.class);

    @Resource
    private PreCmpChargeReqService preCmpChargeService;

    public void procKafkaData(PreCmpChargeReq req) {
        if (req.getReqId() != null && preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                logger.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
