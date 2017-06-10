package com.magic.crius.storage.db;

import com.magic.crius.po.UserTrade;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:32
 */
public interface UserTradeDbService {


    boolean batchSave(Collection<UserTrade> userTrades);
}
