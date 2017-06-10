package com.magic.crius.assemble;

import com.magic.crius.po.UserTrade;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:53
 */
public interface UserTradeAssemService {


    boolean batchSave(Collection<UserTrade> userTrades);

}
