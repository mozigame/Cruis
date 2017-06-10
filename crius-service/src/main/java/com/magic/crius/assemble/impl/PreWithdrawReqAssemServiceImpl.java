package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.*;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    /**
     * 会员出款汇总
     */
    @Resource
    private UserOutMoneySummaryAssemService userOutMoneySummaryAssemService;
    /*会员出款明细*/
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;
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
            List<UserOutMoneySummary> userOutMoneySummaries = new ArrayList<>();
            List<UserOutMoneyDetail> details = new ArrayList<>();
            List<UserAccountSummary> userAccountSummaries = new ArrayList<>();
            List<UserTrade> userTrades = new ArrayList<>();

            for (PreWithdrawReq req : list) {
                /*会员出款汇总*/
                userOutMoneySummaries.add(assembleUserOutMoneySummary(req));
                /*会员出款明细*/
                details.add(assembleUserOutMoneyDetail(req));
                /*会员账号汇总*/
                userAccountSummaries.add(assembleUserAccountSummary(req));
                userTrades.add(assembleUserTrade(req));

            }
            userOutMoneySummaryAssemService.batchSave(userOutMoneySummaries);
            userOutMoneyDetailAssemService.batchSave(details);
            userAccountSummaryAssemService.updateWithdraw(userAccountSummaries);
            userTradeAssemService.batchSave(userTrades);

            //todo  添加成功id

        }
        return false;
    }

    private UserOutMoneySummary assembleUserOutMoneySummary(PreWithdrawReq req) {
        UserOutMoneySummary userOutMoneySummary = new UserOutMoneySummary();
        userOutMoneySummary.setOwnerId(req.getOwnerId());
        userOutMoneySummary.setUserId(req.getUserId());
        userOutMoneySummary.setOrderCount(req.getAmount());
        //todo 提现次数
        userOutMoneySummary.setOutNum(1);
        userOutMoneySummary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userOutMoneySummary;
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

    private UserAccountSummary assembleUserAccountSummary(PreWithdrawReq req) {
        UserAccountSummary userAccountSummary = new UserAccountSummary();
        userAccountSummary.setUserId(req.getUserId());
        //todo 提现次数
        userAccountSummary.setOutNum(1L);
        userAccountSummary.setOutCount(req.getAmount());
        //默认0
        userAccountSummary.setFlowNum(0L);
        userAccountSummary.setFlowCount(0L);
        userAccountSummary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
        return userAccountSummary;
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
