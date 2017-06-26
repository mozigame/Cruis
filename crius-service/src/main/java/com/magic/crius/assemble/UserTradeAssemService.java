package com.magic.crius.assemble;

import com.magic.analysis.enums.ActionType;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.UserTradeService;
import com.magic.crius.vo.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:53
 * 账户交易明细
 */
@Service
public class UserTradeAssemService {


    @Resource
    private UserTradeService userTradeService;

    public boolean batchSave(List<UserTrade> userTrades) {
        if (userTrades != null && userTrades.size() > 0) {
            List<Long> userIds = new ArrayList<>();
            for (UserTrade orderDetail : userTrades) {
                userIds.add(orderDetail.getUserId());
            }
            System.out.println("userTrade size : " + userTrades.size() + " userIds.size : " + userIds.size());
            return userTradeService.batchSave(userTrades, userIds);
        }
        return false;
    }

    public UserTrade assembleUserTrade(CashbackReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_CHU.getStatus());
        userTrade.setActiontype(ActionType.FANG_SHUI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(DiscountReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_CHU.getStatus());
        userTrade.setActiontype(ActionType.YOU_HUI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(OnlChargeReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_RU.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(OperateChargeReq req, Long userId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_RU.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(OperateWithDrawReq req, Long userId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_CHU.getStatus());
        userTrade.setActiontype(ActionType.TI_KUANG.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(PreCmpChargeReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_RU.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(PreWithdrawReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getReqId());
        userTrade.setTradeNum(req.getAmount());
        //todo 账户余额
        userTrade.setTotalNum(0L);
        userTrade.setTradeTime(req.getProduceTime());
        //todo 交易类型
        userTrade.setTradeType(ActionType.ZHUAN_CHU.getStatus());
        //todo 存取类型
        userTrade.setActiontype(ActionType.TI_KUANG.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

}
