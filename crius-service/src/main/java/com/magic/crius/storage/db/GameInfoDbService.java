package com.magic.crius.storage.db;

import java.util.List;
import java.util.Map;

import com.magic.crius.po.GameInfo;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:29
 */
public interface GameInfoDbService {

    boolean batchSave(List<GameInfo> gameInfos);

    boolean deleteAll();

    GameInfo get(GameInfo gameInfo);
    
    /**
     * 
     * @param gameFactoryType
     * @param gameAbstractType
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    Map<String, String> getGameTypeByFactoryMap();
}
