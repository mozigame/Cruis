package com.magic.crius.service.impl;

import com.magic.crius.po.UserTrade;
import com.magic.crius.service.UserTradeService;
import com.magic.crius.storage.db.UserTradeDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:37
 */
@Service
public class UserTradeServiceImpl implements UserTradeService {

    @Resource
    private UserTradeDbService userTradeDbService;

    @Override
    public boolean batchSave(List<UserTrade> userTrades,List<Long> userIds) {
        //return userTradeDbService.batchSave(userTrades, userIds);
        
        for(UserTrade trade:userTrades){
			// 添加审核时间
			if (trade.getTradeTime() == null || trade.getTradeTime() == 0) {
				trade.setTradeTime(System.currentTimeMillis());
			}
			userTradeDbService.saveTrade(trade);
		}
		
		return true;
    }

	@Override
	public boolean updateTradeList(List<UserTrade> userTrades) {
		// TODO Auto-generated method stub
		for(UserTrade trade:userTrades){
			// 添加审核时间
			if (trade.getTradeTime() == null || trade.getTradeTime() == 0) {
				trade.setTradeTime(System.currentTimeMillis());
			}
			userTradeDbService.updateTradeStatus(trade);
		}
		
		return true;
	}

	@Override
	public boolean updateTradeStatus4Failed(List<UserTrade> userTrades) {
		for(UserTrade trade:userTrades){
			// 添加审核时间
			if (trade.getTradeTime() == null || trade.getTradeTime() == 0) {
				trade.setTradeTime(System.currentTimeMillis());
			}
			userTradeDbService.updateTradeStatus4Failed(trade);
		}
		return true;
	}


}
