package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.UserTradeMapper;
import com.magic.crius.po.UserTrade;
import com.magic.crius.storage.db.UserTradeDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:33
 */
@Service
public class UserTradeDbServiceImpl implements UserTradeDbService {

    @Resource
    private UserTradeMapper userTradeMapper;

    @Override
    public boolean batchSave(List<UserTrade> userTrades, List<Long> userIds) {
        try {
            return userTradeMapper.insertBatch(userTrades,userIds) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
