package com.magic.crius.service.impl;

import com.magic.crius.po.UserTrade;
import com.magic.crius.service.UserTradeService;
import com.magic.crius.storage.db.UserTradeDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
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
        return userTradeDbService.batchSave(userTrades, userIds);
    }
}
