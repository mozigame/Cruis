package com.magic.crius.service;

import com.magic.crius.po.UserTrade;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:36
 */
public interface UserTradeService {

    boolean batchSave(List<UserTrade> userTrades,List<Long> userId);
}
