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
    
    /**
     * 
     * @param gameFactoryType
     * @param gameAbstractType
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    List<Map<String, String>> getGameTypeByFactoryAll();
}