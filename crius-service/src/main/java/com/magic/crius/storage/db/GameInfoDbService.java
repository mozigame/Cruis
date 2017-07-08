package com.magic.crius.storage.db;

import com.magic.crius.po.GameInfo;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:29
 */
public interface GameInfoDbService {

    boolean batchSave(List<GameInfo> gameInfos);

    boolean deleteAll();

    GameInfo get(GameInfo gameInfo);
}
