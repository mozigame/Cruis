package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateChargeReqAssemService;
import com.magic.crius.assemble.OwnerOperateFlowDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OperateChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OperateChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private OwnerOperateFlowDetailAssemService ownerOperateFlowDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

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
            List<OwnerOperateFlowDetail> ownerOperateFlowSummmaries = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();
            for (OperateChargeReq req : list) {
                /**
                 * 人工入款汇总
                 */
                ownerOperateFlowSummmaries.add(assembleOwnerOperateFlowDetail(req));

                if (req.getUserIds() != null && req.getUserIds().length > 0) {
                    for (Long userId : req.getUserIds()) {
                        userTrades.add(assembleUserTrade(req, userId));
                    }
                }
            }
            ownerOperateFlowDetailAssemService.batchSave(ownerOperateFlowSummmaries);
            userTradeAssemService.batchSave(userTrades);
        }
        return false;
    }

    private OwnerOperateFlowDetail assembleOwnerOperateFlowDetail(OperateChargeReq req) {
        OwnerOperateFlowDetail summmary = new OwnerOperateFlowDetail();
        summmary.setOwnerId(req.getOwnerId());
        //todo
        summmary.setOperateFlowNum(1);
        summmary.setOperateFlowMoneyCount(req.getAmount());
        summmary.setOperateFlowType(req.getType());
        summmary.setOperateFlowTypeName(req.getRemark());
        summmary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return summmary;
    }

    private UserTrade assembleUserTrade(OperateChargeReq req, Long userId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
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
