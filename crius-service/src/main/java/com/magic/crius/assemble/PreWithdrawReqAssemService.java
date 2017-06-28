package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.PreWithdrawReqAssemService;
import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:32
 * 用户提现
 */
@Service
public class PreWithdrawReqAssemService {
    /**
     * 用户提现
     */
    @Resource
    private PreWithdrawReqService preWithdrawService;
    /*会员出款明细*/
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    public void procKafkaData(PreWithdrawReq req) {
        if (req.getReqId() != null && preWithdrawService.getByReqId(req.getReqId()) == null) {
            if (!preWithdrawService.save(req)) {
                CriusLog.error("save PreWithdrawReq error,reqId : " + req.getReqId());
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
