package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.UserTradeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:54
 */
@Service
public class UserTradeAssemServiceImpl implements UserTradeAssemService {

    @Resource
    private UserTradeService userTradeService;

    @Override
    public boolean batchSave(Collection<UserTrade> userTrades) {
        return userTradeService.batchSave(userTrades);
    }
}
