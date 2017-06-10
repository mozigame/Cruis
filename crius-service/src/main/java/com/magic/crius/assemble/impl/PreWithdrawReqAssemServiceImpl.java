package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.PreWithdrawReqAssemService;
import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:32
 * 用户提现
 */
@Service
public class PreWithdrawReqAssemServiceImpl implements PreWithdrawReqAssemService {
    /**
     * 用户提现
     */
    @Resource
    private PreWithdrawReqService preWithdrawService;
    /*会员出款明细*/
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;
    @Resource
    private UserTradeAssemService userTradeAssemService;

    @Override
    public void procKafkaData(PreWithdrawReq req) {
        if (preWithdrawService.getByReqId(req.getReqId()) == null) {
            if (!preWithdrawService.save(req)) {
                CriusLog.error("save PreWithdrawReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<PreWithdrawReq> list = preWithdrawService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<UserOutMoneyDetail> details = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();

            for (PreWithdrawReq req : list) {
                /*会员出款明细*/
                details.add(assembleUserOutMoneyDetail(req));
                userTrades.add(assembleUserTrade(req));

            }
            userOutMoneyDetailAssemService.batchSave(details);
            userTradeAssemService.batchSave(userTrades);

            //todo  添加成功id

        }
        return false;
    }

    private UserOutMoneyDetail assembleUserOutMoneyDetail(PreWithdrawReq req) {
        UserOutMoneyDetail detail = new UserOutMoneyDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setOrderCount(req.getAmount());
        //TODO 待定
        detail.setTaxCount(0L);
        //TODO 待定
        detail.setState(0);
        //TODO 待定
        detail.setOrderId(0L);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //TODO 待定
        detail.setHandlerId(0L);
        //TODO 待定
        detail.setHandlerName("");
        detail.setCreateTime(req.getProduceTime());
        detail.setUpdateTime(req.getProduceTime());
        return detail;
    }

    private UserTrade assembleUserTrade(PreWithdrawReq req) {
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
