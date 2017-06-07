package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.DiscountReqAssemService;
import com.magic.crius.assemble.OwnerPreferentialSummaryAssemService;
import com.magic.crius.assemble.UserPreferentialSummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.po.UserPreferentialSummary;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.DiscountReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            Map<String, OwnerPreferentialSummary> ownerOnlineFlowSummmaryMap = new HashMap<>();
            Map<String, UserPreferentialSummary> userPreferentialSummaryHashMap = new HashMap<>();

            for (DiscountReq req : list) {
                /*会员优惠汇总*/
                if (ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getStatus()) == null) {
                    OwnerPreferentialSummary summary = new OwnerPreferentialSummary();
                    summary.setOwnerId(req.getOwnerId());
                    summary.setPreferentialMoneyCount(req.getAmount());
                    summary.setPreferentialNum(1);
                    summary.setPreferentialType(req.getStatus());
                    //TODO name 在何处获取
                    summary.setPreferentialTypeName("");
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    ownerOnlineFlowSummmaryMap.put(req.getOwnerId() + "_" + req.getStatus(), summary);
                } else {
                    OwnerPreferentialSummary flow = ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getStatus());
                    flow.setPreferentialMoneyCount(flow.getPreferentialMoneyCount() + req.getAmount());;
                    flow.setPreferentialNum(flow.getPreferentialNum() + 1);;
                }

                /*会员优惠汇总*/
                if (userPreferentialSummaryHashMap.get(req.getUserId() + "_" + req.getStatus()) == null) {
                    UserPreferentialSummary summary = new UserPreferentialSummary();
                    summary.setOwnerId(req.getOwnerId());
                    summary.setUserId(req.getUserId());
                    summary.setPreferentialMoneyCount(req.getAmount());
                    //todo 优惠次数
                    summary.setPreferentialNum(1);
                    summary.setPreferentialType(req.getStatus());
                    //todo 优惠类型名称
                    summary.setPreferentialTypeName("");
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    userPreferentialSummaryHashMap.put(req.getUserId() + "_" + req.getStatus(), summary);
                } else {
                    UserPreferentialSummary summary = new UserPreferentialSummary();
                    summary.setPreferentialMoneyCount(summary.getPreferentialMoneyCount() + req.getAmount());
                    //todo 优惠次数
                    summary.setPreferentialNum(summary.getPreferentialNum() + 1);
                }

            }
            if (ownerOnlineFlowSummmaryMap.size() > 0) {
                ownerPreferentialSummaryAssemService.batchSave(ownerOnlineFlowSummmaryMap);
            }
            if (userPreferentialSummaryHashMap.size() > 0) {
                userPreferentialSummaryAssemService.batchSave(userPreferentialSummaryHashMap);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
