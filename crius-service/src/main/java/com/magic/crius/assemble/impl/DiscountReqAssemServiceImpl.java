package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.DiscountReqAssemService;
import com.magic.crius.assemble.OwnerPreferentialSummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerPreferentialSummary;
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
 * 优惠汇总
 */
@Service
public class DiscountReqAssemServiceImpl implements DiscountReqAssemService {

    @Resource
    private DiscountReqService discountReqService;
    @Resource
    private OwnerPreferentialSummaryAssemService ownerPreferentialSummaryAssemService;

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
            for (DiscountReq req : list) {
                if (ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getStatus()) == null) {
                    OwnerPreferentialSummary flow = new OwnerPreferentialSummary();
                    flow.setOwnerId(req.getOwnerId());
                    flow.setPreferentialMoneyCount(req.getAmount());
                    flow.setPreferentialNum(1);
                    flow.setPreferentialType(req.getStatus());
                    flow.setPreferentialTypeName("");   //TODO name 在何处获取
                    flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    ownerOnlineFlowSummmaryMap.put(req.getOwnerId() + "_" + req.getStatus(), flow);
                } else {
                    OwnerPreferentialSummary flow = ownerOnlineFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getStatus());
                    flow.setPreferentialMoneyCount(flow.getPreferentialMoneyCount() + req.getAmount());;
                    flow.setPreferentialNum(flow.getPreferentialNum() + 1);;
                }
            }
            if (ownerOnlineFlowSummmaryMap.size() > 0) {
                ownerPreferentialSummaryAssemService.batchSave(ownerOnlineFlowSummmaryMap.values());
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
