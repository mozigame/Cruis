package com.magic.crius.dao.tethys.db;

import com.magic.crius.po.UserTrade;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface UserTradeMapper {

    int insert(UserTrade record);

    int batchInsert(Collection<UserTrade> userTrades);
}