package com.magic.crius.service;

import com.magic.crius.po.GameInfo;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:31
 */
public interface GameInfoService {

    boolean batchSave(Collection<GameInfo> gameInfos);
}
