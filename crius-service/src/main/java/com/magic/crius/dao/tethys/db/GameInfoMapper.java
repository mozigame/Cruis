package com.magic.crius.dao.tethys.db;

import com.magic.crius.po.GameInfo;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * tethys 中的gameInfo
 */
@Component
public interface GameInfoMapper {


    int insert(GameInfo record);

    int batchInsert(Collection<GameInfo> gameInfos);
}