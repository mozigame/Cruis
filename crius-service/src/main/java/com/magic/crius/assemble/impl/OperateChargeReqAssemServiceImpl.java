package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateChargeReqAssemService;
import com.magic.crius.assemble.OwnerOperateFlowSummmaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerOperateFlowSummmary;
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
    @Resource
    private OwnerOperateFlowSummmaryAssemService ownerOperateFlowSummmaryAssemService;

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
            }
            if (ownerOperateFlowSummmaryMap.size() > 0) {
                ownerOperateFlowSummmaryAssemService.batchSave(ownerOperateFlowSummmaryMap.values());
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
