package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
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

    @Resource
    private PreCmpChargeReqService preCmpChargeService;

    public void procKafkaData(PreCmpChargeReq req) {
        if (req.getReqId() != null && preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                CriusLog.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
