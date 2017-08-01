package com.magic.crius.storage.db.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.magic.crius.dao.tethys.db.GameInfoMapper;
import com.magic.crius.po.GameInfo;
import com.magic.crius.storage.db.GameInfoDbService;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:30
 */
@Service
public class GameInfoDbServiceImpl implements GameInfoDbService {

    private static final Logger logger = Logger.getLogger(GameInfoDbServiceImpl.class);

    @Resource(name = "criusGameInfoMapper")
    private com.magic.crius.dao.crius.db.GameInfoMapper criusGameInfoMapper;

    @Override
    public boolean batchSave(List<GameInfo> gameInfos) {
        if (criusGameInfoMapper.insertBatch(gameInfos) <= 0) {
            logger.warn("criusGameInfoMapper insert gameInfos failed ");
            return false;
        } else {
            logger.info("criusGameInfoMapper insert gameInfos success");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        if (!criusGameInfoMapper.deleteAll()) {
            logger.warn("criusGameInfoMapper deleteall gameInfos failed ");
        }
        return true;
    }
    
    public Long updateBatch(List<GameInfo> list){
    	return criusGameInfoMapper.updateBatch(list);
    }
    
    public boolean deleteByGameId(List<String> gameIdList){
    	return criusGameInfoMapper.deleteByGameId(gameIdList);
    }

    @Override
    public GameInfo get(GameInfo gameInfo) {
        return criusGameInfoMapper.get(gameInfo);
    }
    
    public List<GameInfo> findGameList(GameInfo info){
    	return criusGameInfoMapper.findGameList(info);
    }
    
    /**
     * 
     * @return map<key=gameFactoryType+"-"+gameAbstractType, value=gameType>
     */
    public Map<String, String> getGameTypeByFactoryMap(){
    	List<Map<String, String>> list= criusGameInfoMapper.getGameTypeByFactoryAll();
    	Map<String, String> map=new HashMap<>();
    	for(Map<String, String> m:list){
    		map.put(m.get("game_key"), m.get("game_type"));
    	}
    	return map;
    }

    @Override
    public String getGameType(String factoryType, String abstractType) {
        return criusGameInfoMapper.getGameType(factoryType,abstractType);
    }
}
