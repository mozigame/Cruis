package com.magic.crius.assemble;

import com.magic.analysis.enums.ActionType;
import com.magic.analysis.enums.FlowType;
import com.magic.analysis.enums.OutType;
import com.magic.analysis.enums.SummaryType;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.UserTradeService;
import com.magic.crius.vo.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    private static Logger logger = Logger.getLogger(UserTradeAssemService.class);
    @Resource
    private UserTradeService userTradeService;

    public boolean batchSave(List<UserTrade> userTrades) {
        if (userTrades != null && userTrades.size() > 0) {
            List<Long> userIds = new ArrayList<>();
           // List<Long> userIdList=null;
            //List<UserTrade> tradeList=null;
            for (UserTrade orderDetail : userTrades) {
//            	userIdList=new ArrayList<>();
//            	tradeList=new ArrayList<>();
//            	tradeList.add(orderDetail);
//            	userIdList.add(orderDetail.getUserId());
                userIds.add(orderDetail.getUserId());
            	//userTradeService.batchSave(tradeList, userIdList);
                //userTradeService.
            }
            logger.info("userTrade size : " + userTrades.size() + " userIds.size : " + userIds.size());
            return userTradeService.batchSave(userTrades, userIds);
           // return true;
        }
        return false;
    }
    
    
    public boolean batchUpdate(List<UserTrade> userTrades) {
        if (userTrades==null||userTrades.size()==0) {
        	return false;
        }
        logger.info("batchUpdate userTrade size : " + userTrades.size() );
        return userTradeService.updateTradeList(userTrades);
      
    }

    public boolean updateTradeStatus4Failed(List<UserTrade> userTrades) {
        if (userTrades==null||userTrades.size()==0) {
        	return false;
        }
        logger.info("batchUpdate updateTradeStatus4Failed size : " + userTrades.size() );
        return userTradeService.updateTradeStatus4Failed(userTrades);

    }

    public UserTrade assembleUserTrade(CashbackReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getBillId());
        userTrade.setTradeNum(req.getAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(ActionType.ZHUAN_CHU.getStatus());
        userTrade.setActiontype(ActionType.FANG_SHUI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        userTrade.setGameType(req.getGamePlatformHalltypeId());
        return userTrade;
    }

    public UserTrade assembleUserTrade(DiscountReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getBillId());
        userTrade.setTradeNum(req.getOfferAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(req.getOfferTypeId());
        userTrade.setRemark(req.getRemark());
        userTrade.setActiontype(ActionType.YOU_HUI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        return userTrade;
    }

    public UserTrade assembleUserTrade(OnlChargeReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getBillId());
        userTrade.setTradeNum(req.getChargeAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(SummaryType.ONLINE_CHARGE.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));

        userTrade.setRemark(SummaryType.ONLINE_CHARGE.getStatusName());
        return userTrade;
    }

    public UserTrade assembleUserTrade(OperateChargeReq req, Long userId, Long billId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
        userTrade.setTradeId(billId);
        userTrade.setTradeNum(req.getChargeAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setRemark(req.getRemark());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(SummaryType.ARTIFICIAL_INTO_MOENY.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));

        FlowType flowType = FlowType.getState(req.getType() == null ? 0 : req.getType());
        userTrade.setRemark(flowType == null ? "" : flowType.getMethodName());
        return userTrade;
    }

    public UserTrade assembleUserTrade(OperateWithDrawReq req, Long userId, Long billId) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(userId);
        userTrade.setTradeId(billId);
        userTrade.setTradeNum(req.getAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setRemark(req.getRemark());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(SummaryType.ARTIFICIAL_WITHDRAWAL.getStatus());
        userTrade.setActiontype(ActionType.TI_KUANG.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));

        OutType flowType = OutType.getState(req.getWithdrawType() == null ? 0 : req.getWithdrawType());
        userTrade.setRemark(flowType == null ? "" : flowType.getMethodName());
        return userTrade;
    }

    public UserTrade assembleUserTrade(PreCmpChargeReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getBillId());
        userTrade.setTradeNum(req.getChargeAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(SummaryType.COMPANY_INCOME.getStatus());
        userTrade.setActiontype(ActionType.CHONG_ZHI.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //todo 状态改为100表示通过 枚举
        userTrade.setStatus(100);

        userTrade.setRemark(SummaryType.COMPANY_INCOME.getStatusName());
        return userTrade;
    }

    public UserTrade assembleUserTrade(PreWithdrawReq req) {
        UserTrade userTrade = new UserTrade();
        userTrade.setOwnerId(req.getOwnerId());
        userTrade.setUserId(req.getUserId());
        userTrade.setTradeId(req.getBillId());
        userTrade.setTradeNum(req.getReqWithdrawAmount());
        userTrade.setTotalNum(req.getBalance());
        userTrade.setTradeTime(req.getProduceTime());
        userTrade.setTradeType(SummaryType.USER_OUT_MONEY.getStatus());
        userTrade.setActiontype(ActionType.TI_KUANG.getStatus());
        userTrade.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
        //todo 状态改为100表示通过
        userTrade.setStatus(100);

        userTrade.setRemark(SummaryType.USER_OUT_MONEY.getStatusName());
        return userTrade;
    }


	

}
