package com.magic.crius.dao.crius.db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.magic.crius.po.GameInfo;

/**
 * tethys 中的gameInfo
 */
@Component("criusGameInfoMapper")
public interface GameInfoMapper {

    /**
     * 批量插入
     * @param gameInfos
     * @return
     */
    Long insertBatch(@Param("param") List<GameInfo> gameInfos);
    
    Long updateBatch(List<GameInfo> list);
    
    boolean deleteByGameId(List<String> gameIdList);

    /**
     * 删除所有
     * @return
     */
    boolean deleteAll();
    
   

    /**
     * 获取游戏详情
     * @param info
     * @return
     */
    GameInfo get(GameInfo info);
    
    List<GameInfo> findGameList(GameInfo info); 
    
    /**
     * 
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    List<Map<String, String>> getGameTypeByFactoryAll();

    /**
     * 获取游戏类型
     * @param factoryType
     * @param abstractType
     * @return
     */
    String getGameType(@Param("factoryType") String factoryType, @Param("abstractType") String abstractType);
}