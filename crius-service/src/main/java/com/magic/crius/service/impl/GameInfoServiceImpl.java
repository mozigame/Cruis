package com.magic.crius.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.po.GameInfo;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.storage.db.GameInfoDbService;
import com.magic.crius.storage.redis.GameInfoRedisService;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:31
 */
@Service
public class GameInfoServiceImpl implements GameInfoService {

    @Resource
    private GameInfoDbService gameInfoDbService;
    @Resource
    private GameInfoRedisService gameInfoRedisService;

    @Override
    public boolean batchSave(List<GameInfo> gameInfos) {
        return gameInfoDbService.batchSave(gameInfos);
    }

    @Override
    public boolean deleteAll() {
        return gameInfoDbService.deleteAll();
    }

    @Override
    public boolean getLock() {
        return gameInfoRedisService.getLock();
    }

    @Override
    public boolean setLock() {
        return gameInfoRedisService.setLock();
    }

    @Override
    public GameInfo get(GameInfo gameInfo) {
        return gameInfoDbService.get(gameInfo);
    }
    
    /**
     * 
     * @param gameFactoryType
     * @param gameAbstractType
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    public Map<String, String> getGameTypeByFactoryMap(){
    	return gameInfoDbService.getGameTypeByFactoryMap();
    }
}
