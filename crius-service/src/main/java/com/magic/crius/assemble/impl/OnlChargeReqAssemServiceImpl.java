package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OnlChargeReqAssemService;
import com.magic.crius.assemble.OwnerOnlineFlowSummmaryAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.assemble.UserFlowMoneyDetailAssemService;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private OwnerOnlineFlowSummmaryAssemService ownerOnlineFlowSummmaryAssemService;
    /*会员入款明细*/
    @Resource
    private UserFlowMoneyDetailAssemService userFlowMoneyDetailAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;

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
            Map<String, OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaryMap = new HashMap<>();
            List<UserFlowMoneyDetail> userFlowMoneyDetails = new ArrayList<>();
            Map<Long, UserAccountSummary> userAccountSummaryMap = new HashMap<>();
            for (OnlChargeReq req : list) {
                /*线上入款汇总*/
                if (ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getMerchantCode()) == null) {
                    OwnerOnlineFlowSummmary flow = new OwnerOnlineFlowSummmary();
                    flow.setOwnerId(req.getOwnerId());
                    flow.setOperateFlowMoneyCount(req.getAmount());
                    flow.setOperateFlowNum(1);
                    flow.setMerchantCode(req.getMerchantCode());
                    flow.setMerchantName(req.getMerchantName());
                    flow.setPaySystemCode(req.getPaySystemCode());
                    flow.setPaySystemName(req.getPaySystemName());
                    flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    ownerOnlineFlowSummmaryMap.put(req.getOwnerId() + "_" + req.getMerchantCode(), flow);
                } else {
                    OwnerOnlineFlowSummmary flow = ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getMerchantCode());
                    flow.setOperateFlowMoneyCount(flow.getOperateFlowMoneyCount() + req.getAmount());
                    flow.setOperateFlowNum(flow.getOperateFlowNum() + 1);
                }

                /*会员入款详情*/
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
                userFlowMoneyDetails.add(detail);

                /*会员账号汇总*/
                if (userAccountSummaryMap.get(req.getUserId()) == null) {
                    UserAccountSummary summary = new UserAccountSummary();
                    summary.setOwnerId(req.getOwnerId());
                    summary.setUserId(req.getUserId());
                    //todo 充值次数次数
                    summary.setFlowNum(1L);
                    summary.setFlowCount(req.getAmount());

                    summary.setOutNum(0L);
                    summary.setOutCount(0L);
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    userAccountSummaryMap.put(req.getUserId(), summary);
                } else {
                    UserAccountSummary summary = userAccountSummaryMap.get(req.getUserId());
                    summary.setFlowNum(summary.getFlowNum() + 1);
                    summary.setFlowCount(summary.getFlowCount() + req.getAmount());
                }
            }
            if (ownerOnlineFlowSummmaryMap.size() > 0) {
                ownerOnlineFlowSummmaryAssemService.batchSave(ownerOnlineFlowSummmaryMap);
            }
            if (userFlowMoneyDetails.size() > 0) {
                userFlowMoneyDetailAssemService.batchSave(userFlowMoneyDetails);
            }
            if (userAccountSummaryMap.size() > 0) {
                userAccountSummaryAssemService.updateRecharge(userAccountSummaryMap);
            }
        }
    }
}
