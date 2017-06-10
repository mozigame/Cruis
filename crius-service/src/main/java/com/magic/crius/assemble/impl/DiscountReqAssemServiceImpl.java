package com.magic.crius.assemble.impl;

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
public class DiscountReqAssemServiceImpl implements DiscountReqAssemService {

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
            List<OwnerPreferentialDetail> ownerOnlineFlowDetailMap = new ArrayList<>();
            List<UserPreferentialDetail> userPreferentialDetailHashMap = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (DiscountReq req : list) {
                /*会员优惠汇总*/
                ownerOnlineFlowDetailMap.add(assembleOwnerPreferentialDetail(req));
                /*会员优惠汇总*/
                userPreferentialDetailHashMap.add(assembleUserPreferentialDetail(req));
                /*账户交易明细*/
                userTrades.add(assembleUserTrade(req));
            }
            ownerPreferentialDetailAssemService.batchSave(ownerOnlineFlowDetailMap);
            userPreferentialDetailAssemService.batchSave(userPreferentialDetailHashMap);
            userTradeAssemService.batchSave(userTrades);

        }
        return false;
    }

    private OwnerPreferentialDetail assembleOwnerPreferentialDetail(DiscountReq req) {
        OwnerPreferentialDetail ownerPreferentialDetail = new OwnerPreferentialDetail();
        ownerPreferentialDetail.setOwnerId(req.getOwnerId());
        ownerPreferentialDetail.setPreferentialMoneyCount(req.getAmount());
        ownerPreferentialDetail.setPreferentialNum(1);
        ownerPreferentialDetail.setPreferentialType(req.getStatus());
        //TODO name 在何处获取
        ownerPreferentialDetail.setPreferentialTypeName("");
        ownerPreferentialDetail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return ownerPreferentialDetail;
    }

    private UserPreferentialDetail assembleUserPreferentialDetail(DiscountReq req) {
        UserPreferentialDetail detail = new UserPreferentialDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setPreferentialMoneyCount(req.getAmount());
        //todo 优惠次数
        detail.setPreferentialNum(1);
        detail.setPreferentialType(req.getStatus());
        //todo 优惠类型名称
        detail.setPreferentialTypeName("");
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return detail;
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
