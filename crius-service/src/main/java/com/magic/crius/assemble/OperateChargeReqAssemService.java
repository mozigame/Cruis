package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateChargeReqAssemService;
import com.magic.crius.assemble.OwnerOperateFlowDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:47
 * 人工充值
 */
@Service
public class OperateChargeReqAssemService  {

    @Resource
    private OperateChargeReqService operateChargeService;
    /*人工入款汇总*/
    @Resource
    private OwnerOperateFlowDetailAssemService ownerOperateFlowDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    public void procKafkaData(OperateChargeReq req) {
        if (operateChargeService.getByReqId(req.getReqId()) == null) {
            if (!operateChargeService.save(req)) {
                CriusLog.error("save OperateChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

}
