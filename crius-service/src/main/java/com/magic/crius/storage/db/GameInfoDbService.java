package com.magic.crius.storage.db;

import com.magic.crius.po.GameInfo;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:29
 */
public interface GameInfoDbService {

    boolean batchSave(Collection<GameInfo> gameInfos);
}
