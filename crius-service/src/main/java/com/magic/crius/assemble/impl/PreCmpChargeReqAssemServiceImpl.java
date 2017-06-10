package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyAccountDetailAssemService;
import com.magic.crius.assemble.OwnerCompanyFlowDetailAssemService;
import com.magic.crius.assemble.PreCmpChargeReqAssemService;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:32
 * 公司入款
 */
@Service
public class PreCmpChargeReqAssemServiceImpl implements PreCmpChargeReqAssemService {

    @Resource
    private PreCmpChargeReqService preCmpChargeService;

    @Override
    public void procKafkaData(PreCmpChargeReq req) {
        if (preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                CriusLog.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {

        return false;
    }
}
