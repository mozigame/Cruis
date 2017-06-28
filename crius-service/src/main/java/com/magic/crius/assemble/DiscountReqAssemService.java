package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.DiscountReqAssemService;
import com.magic.crius.assemble.OwnerPreferentialDetailAssemService;
import com.magic.crius.assemble.UserPreferentialDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:40
 * 优惠赠送
 */
@Service
public class DiscountReqAssemService  {

    @Resource
    private DiscountReqService discountReqService;
    /*业主优惠汇总*/
    @Resource
    private OwnerPreferentialDetailAssemService ownerPreferentialDetailAssemService;
    /*会员优惠汇总*/
    @Resource
    private UserPreferentialDetailAssemService userPreferentialDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    public void procKafkaData(DiscountReq req) {
        if (req.getReqId() != null && discountReqService.getByReqId(req.getReqId()) == null) {
            if (!discountReqService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
