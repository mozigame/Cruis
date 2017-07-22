package com.magic.crius.storage.db;

import com.magic.crius.po.UserTrade;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:32
 */
public interface UserTradeDbService {


    boolean batchSave(List<UserTrade> userTrades, List<Long> userIds);
    
    boolean updateTradeStatus(UserTrade userTrade);

    boolean updateTradeStatus4Failed(UserTrade userTrade);

    boolean saveTrade(UserTrade userTrade);
}
