package com.magic.crius.service;

import com.magic.crius.po.GameInfo;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:31
 */
public interface GameInfoService {

    /**
     * 批量添加游戏基础信息
     * @param gameInfos
     * @return
     */
    boolean batchSave(List<GameInfo> gameInfos);
    
    Long updateBatch(List<GameInfo> list);
    
    boolean deleteByGameId(List<String> gameIdList);

    /**
     * 删除全部游戏信息
     * @return
     */
    boolean deleteAll();

    /**
     * 获取操作游戏的锁
     * @return
     */
    boolean getLock();

    /**
     * 设置操作游戏时的锁
     * @return
     */
    boolean setLock();

    /**
     * 获取游戏详情
     * @param gameInfo
     * @return
     */
    GameInfo get(GameInfo gameInfo);
    
    List<GameInfo> findGameList(GameInfo info);
    
    /**
     * 
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    Map<String, String> getGameTypeByFactoryMap();

    /**
     * 获取游戏类型
     * @param factoryType
     * @param abstractType
     * @return
     */
    String getGameType(String factoryType,String abstractType);

    /**
     * 根据gameType随机拿取最大的gameId
     * @param gameType
     * @return
     */
    String getGameId(String gameType);
}
