package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OnlChargeReqAssemService;
import com.magic.crius.assemble.OwnerOnlineFlowSummmaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OnlChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private OwnerOnlineFlowSummmaryAssemService ownerOnlineFlowSummmaryAssemService;


    @Override
    public void procKafkaData(OnlChargeReq req) {
        if (onlChargeService.getByReqId(req.getReqId()) == null) {
            if (!onlChargeService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<OnlChargeReq> list = onlChargeService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            Map<String, OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaryMap = new HashMap<>();
            for (OnlChargeReq req : list) {
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
            }
            if (ownerOnlineFlowSummmaryMap.size() > 0) {
                ownerOnlineFlowSummmaryAssemService.batchSave(ownerOnlineFlowSummmaryMap.values());
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
