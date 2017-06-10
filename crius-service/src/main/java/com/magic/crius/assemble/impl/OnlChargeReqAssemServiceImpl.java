package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OnlChargeReqAssemService;
import com.magic.crius.assemble.UserFlowMoneyDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerOnlineFlowDetail;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 * 用户充值成功
 */
@Service
public class OnlChargeReqAssemServiceImpl implements OnlChargeReqAssemService {


    @Resource
    private OnlChargeReqService onlChargeService;
    /*线上入款汇总*/
    @Resource
    private OwnerOnlineFlowDetailAssemServiceImpl ownerOnlineFlowDetailAssemService;
    /*会员入款明细*/
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Override
    public void procKafkaData(OnlChargeReq req) {
        if (onlChargeService.getByReqId(req.getReqId()) == null) {
            if (!onlChargeService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public void convertData(Date date) {
        List<OnlChargeReq> list = onlChargeService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<OwnerOnlineFlowDetail> ownerOnlineFlowSummmaries = new ArrayList<>();
            List<UserFlowMoneyDetail> userFlowMoneyDetails = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (OnlChargeReq req : list) {
                /*线上入款汇总*/
                ownerOnlineFlowSummmaries.add(assembleOwnerOnlineFlowDetail(req));
                /*会员入款详情*/
                userFlowMoneyDetails.add(assembleUserFlowMoneyDetail(req));

                userTrades.add(assembleUserTrade(req));
            }
            ownerOnlineFlowDetailAssemService.batchSave(ownerOnlineFlowSummmaries);
            userFlowMoneyDetailAssemService.batchSave(userFlowMoneyDetails);
            userTradeAssemService.batchSave(userTrades);
        }
    }

    private OwnerOnlineFlowDetail assembleOwnerOnlineFlowDetail(OnlChargeReq req) {
        OwnerOnlineFlowDetail flow = new OwnerOnlineFlowDetail();
        flow.setOwnerId(req.getOwnerId());
        flow.setOperateFlowMoneyCount(req.getAmount());
        //todo
        flow.setOperateFlowNum(1);
        flow.setMerchantCode(req.getMerchantCode());
        flow.setMerchantName(req.getMerchantName());
        flow.setPaySystemCode(req.getPaySystemCode());
        flow.setPaySystemName(req.getPaySystemName());
        flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return flow;
    }

    private UserFlowMoneyDetail assembleUserFlowMoneyDetail(OnlChargeReq req) {
        UserFlowMoneyDetail detail = new UserFlowMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setMerchantCode(req.getMerchantCode());
        detail.setMerchantName(req.getMerchantName());
        detail.setOrderCount(req.getAmount());
        //Todo 待确定
        detail.setState(0);
        //todo 待确定
        detail.setPayMethod(123);
        detail.setFlowId(req.getOrderId());
        //TODO 待确定
        detail.setFlowType(1);
        detail.setOrderId(req.getOrderId());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        return detail;
    }

    private UserTrade assembleUserTrade(OnlChargeReq req) {
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
