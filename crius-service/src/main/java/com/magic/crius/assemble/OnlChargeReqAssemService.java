package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.utils.StringUtils;
import com.magic.crius.assemble.UserFlowMoneyDetailAssemService;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 * 用户充值成功
 */
@Service
public class OnlChargeReqAssemService  {


    @Resource
    private OnlChargeReqService onlChargeService;
    /*线上入款汇总*/
    @Resource
    private OwnerOnlineFlowDetailAssemService ownerOnlineFlowDetailAssemService;
    /*会员入款明细*/
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    public void procKafkaData(OnlChargeReq req) {
        if (req.getReqId() != null && onlChargeService.getByReqId(req.getReqId()) == null) {
            if (!onlChargeService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
