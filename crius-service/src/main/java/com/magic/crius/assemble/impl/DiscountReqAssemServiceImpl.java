package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.DiscountReqAssemService;
import com.magic.crius.assemble.OwnerPreferentialSummaryAssemService;
import com.magic.crius.assemble.UserPreferentialSummaryAssemService;
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
public class DiscountReqAssemServiceImpl implements DiscountReqAssemService {

    @Resource
    private DiscountReqService discountReqService;
    /*业主优惠汇总*/
    @Resource
    private OwnerPreferentialSummaryAssemService ownerPreferentialSummaryAssemService;
    /*会员优惠汇总*/
    @Resource
    private UserPreferentialSummaryAssemService userPreferentialSummaryAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Override
    public void procKafkaData(DiscountReq req) {
        if (discountReqService.getByReqId(req.getReqId()) == null) {
            if (!discountReqService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<DiscountReq> list = discountReqService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<OwnerPreferentialDetail> ownerOnlineFlowSummmaryMap = new ArrayList<>();
            List<UserPreferentialDetail> userPreferentialSummaryHashMap = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (DiscountReq req : list) {
                /*会员优惠汇总*/
                ownerOnlineFlowSummmaryMap.add(assembleOwnerPreferentialSummary(req));
                /*会员优惠汇总*/
                userPreferentialSummaryHashMap.add(assembleUserPreferentialSummary(req));
                /*账户交易明细*/
                userTrades.add(assembleUserTrade(req));
            }
            ownerPreferentialSummaryAssemService.batchSave(ownerOnlineFlowSummmaryMap);
            userPreferentialSummaryAssemService.batchSave(userPreferentialSummaryHashMap);
            userTradeAssemService.batchSave(userTrades);

        }
        return false;
    }

    private OwnerPreferentialDetail assembleOwnerPreferentialSummary(DiscountReq req) {
        OwnerPreferentialDetail ownerPreferentialSummary = new OwnerPreferentialDetail();
        ownerPreferentialSummary.setOwnerId(req.getOwnerId());
        ownerPreferentialSummary.setPreferentialMoneyCount(req.getAmount());
        ownerPreferentialSummary.setPreferentialNum(1);
        ownerPreferentialSummary.setPreferentialType(req.getStatus());
        //TODO name 在何处获取
        ownerPreferentialSummary.setPreferentialTypeName("");
        ownerPreferentialSummary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return ownerPreferentialSummary;
    }

    private UserPreferentialDetail assembleUserPreferentialSummary(DiscountReq req) {
        UserPreferentialDetail summary = new UserPreferentialDetail();
        summary.setOwnerId(req.getOwnerId());
        summary.setUserId(req.getUserId());
        summary.setPreferentialMoneyCount(req.getAmount());
        //todo 优惠次数
        summary.setPreferentialNum(1);
        summary.setPreferentialType(req.getStatus());
        //todo 优惠类型名称
        summary.setPreferentialTypeName("");
        summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return summary;
    }

    private UserTrade assembleUserTrade(DiscountReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(0);
        //todo 存取类型
        userTrade.setActiontype(0);
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }
}
