package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateChargeReqAssemService;
import com.magic.crius.assemble.OwnerOperateFlowSummmaryAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:47
 * 人工充值
 */
@Service
public class OperateChargeReqAssemServiceImpl implements OperateChargeReqAssemService {

    @Resource
    private OperateChargeReqService operateChargeService;
    /*人工入款汇总*/
    @Resource
    private OwnerOperateFlowSummmaryAssemService ownerOperateFlowSummmaryAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;

    @Override
    public void procKafkaData(OperateChargeReq req) {
        if (operateChargeService.getByReqId(req.getReqId()) == null) {
            if (!operateChargeService.save(req)) {
                CriusLog.error("save OperateChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<OperateChargeReq> list = operateChargeService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            Map<String, OwnerOperateFlowSummmary> ownerOperateFlowSummmaryMap = new HashMap<>();
            Map<Long, UserAccountSummary> userAccountSummaryMap = new HashMap<>();
            for (OperateChargeReq req : list) {
                if (ownerOperateFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getType()) == null) {
                    OwnerOperateFlowSummmary summmary = new OwnerOperateFlowSummmary();
                    summmary.setOwnerId(req.getOwnerId());
                    summmary.setOperateFlowNum(1);
                    summmary.setOperateFlowMoneyCount(req.getAmount());
                    summmary.setOperateFlowType(req.getType());
                    summmary.setOperateFlowTypeName(req.getRemark());
                    summmary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                    ownerOperateFlowSummmaryMap.put(req.getOwnerId() + "_" + req.getType(), summmary);
                } else {
                    OwnerOperateFlowSummmary summmary = ownerOperateFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getType());
                    summmary.setOperateFlowNum(summmary.getOperateFlowNum() + 1);
                    summmary.setOperateFlowMoneyCount(summmary.getOperateFlowMoneyCount() + req.getAmount());
                }
                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (Long userId : req.getUserIds()) {
                        if (userAccountSummaryMap.get(userId) != null) {
                            UserAccountSummary summary = new UserAccountSummary();
                            summary.setUserId(userId);
                            summary.setFlowNum(1L);
                            summary.setFlowCount(req.getAmount());
                            summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                            userAccountSummaryMap.put(userId, summary);
                        } else {
                            UserAccountSummary summary = userAccountSummaryMap.get(userId);
                            summary.setFlowNum(summary.getFlowNum() + 1);
                            summary.setFlowCount(summary.getFlowCount() + req.getAmount());
                        }
                    }

                }
            }
            if (ownerOperateFlowSummmaryMap.size() > 0) {
                ownerOperateFlowSummmaryAssemService.batchSave(ownerOperateFlowSummmaryMap);
            }
            if (userAccountSummaryMap.size() > 0) {
                userAccountSummaryAssemService.updateRecharge(userAccountSummaryMap);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
