package com.magic.crius.assemble;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magic.analysis.utils.JsonKit;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.po.GameInfo;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.service.thrift.CriusThirdThriftService;

/**
 * User: joey
 * Date: 2017/6/26
 * Time: 12:43
 */
@Component
public class GameInfoAssemService {

    private static Logger logger = Logger.getLogger(GameInfoAssemService.class);
    @Resource
    private CriusThirdThriftService criusThirdThriftService;
    @Resource
    private GameInfoService gameInfoService;


    private ExecutorService gameInfoTaskPool = Executors.newSingleThreadExecutor();

    public void init() {
        gameInfoTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                getAllGames();
            }
        });
    }
    
    private String getGameInfoStr(GameInfo gameInfo) throws Exception{
    	Map<String, Object> map;
		map = JsonKit.parseToMap(gameInfo);
		return map.toString();
    }

    public void getAllGames() {
        String body = "{\"api\":\"game_list\",\"status\":1}";
        EGResp resp = criusThirdThriftService.getAllGames(body, "account");
        if (resp != null && resp.getCode() == 0) {
            long startTime = System.currentTimeMillis();
            JSONArray jsonObj = JSONArray.parseArray(resp.getData());
            List<GameInfo> gameInfos = new ArrayList<>();
            for (Object j : jsonObj) {
                JSONObject obj1 = (JSONObject) j;
                GameInfo gameInfo = JSONObject.parseObject(obj1.toJSONString(), GameInfo.class);
                gameInfos.add(gameInfo);
            }
//            if (!gameInfoService.getLock()) {
//                if (!gameInfoService.setLock()) {
//                    logger.error("proc gameInfo set lock error");
//                } else {
                	//检查是否有修改，有修改才对数据进行更新
                	if(checkChange(gameInfos)){
	                    //先清空游戏表
	                    if (!gameInfoService.deleteAll()) {
	                        logger.warn("delete gameInfos failed");
	                    }
	                    if (!batchSaveGame(gameInfos)) {
	                        logger.warn("batchSave gameInfos failed");
	                    }
                	}
//                }
//            }
            logger.info("insert all gameInfo spend time " +(System.currentTimeMillis() - startTime));

        }
    }
    
    /**
     * 对游戏按批次保存,每个批次500行
     * @param gameList
     * @return
     */
    private boolean batchSaveGame(List<GameInfo> gameList){
    	int BATCH_SIZE=500;
    	List<GameInfo> list=new ArrayList<>();
    	boolean result=false;
    	for(GameInfo game:list){
    		list.add(game);
    		if(list.size()>BATCH_SIZE){
    			result=gameInfoService.batchSave(list);
    			if(!result){
    				return result;
    			}
    			list.clear();
    		}
    	}
    	if(list.size()>0){
    		result=gameInfoService.batchSave(list);
    	}
    	return result;
    }
    
    private boolean checkChange(List<GameInfo> gameInfos){
    	try {
			List<GameInfo> gameList=this.gameInfoService.findGameList(new GameInfo());
			if(gameInfos.size()!=gameList.size()){//数据量不一样，说明有修改
				logger.info("-----checkChange--gameInfos="+gameInfos.size()+" gameList="+gameList.size());
				return true;
			}
			List<GameInfo> changeList=new ArrayList<>();
			List<GameInfo> newList=new ArrayList<>();
			boolean isExist=false;
			for(GameInfo game:gameInfos){
				isExist=false;
				for(GameInfo gameInfo:gameList){
					if(game.getGameId().equals(gameInfo.getGameId())){
						isExist=true;
			    		if(!this.getGameInfoStr(game).equals(this.getGameInfoStr(gameInfo))){
			    			changeList.add(game);
			    		}
			    		break;
					}
				}
				if(!isExist){
					newList.add(game);
				}
			}
			logger.info("-----checkChange--gameList="+gameList.size()+" changeList="+changeList.size()+" newList="+newList.size());
			if(newList.size()>0 || changeList.size()>0){//有修改或新增，表示有修改
				return true;
			}
			return false;
		} catch (Exception e) {
			return true;
		}
    }

    /**
     * 检查有修改才更新数据
     * 因涉及tethys接口，此功能暂时不用
     * @deprecated
     * @param gameInfos
     * @throws Exception
     */
	private boolean saveGameInfoOnChange(List<GameInfo> gameInfos) {
		try {
			List<GameInfo> gameList=this.gameInfoService.findGameList(new GameInfo());
			List<GameInfo> changeList=new ArrayList<>();
			List<GameInfo> newList=new ArrayList<>();
			boolean isExist=false;
			for(GameInfo game:gameInfos){
				isExist=false;
				for(GameInfo gameInfo:gameList){
					if(game.getGameId().equals(gameInfo.getGameId())){
						isExist=true;
			    		if(!this.getGameInfoStr(game).equals(this.getGameInfoStr(gameInfo))){
			    			changeList.add(game);
			    		}
			    		break;
					}
				}
				if(!isExist){
					newList.add(game);
				}
			}
			
			List<String> delGameIdList=new ArrayList<>(); 
			//检查不存在就删除
			for(GameInfo gameInfo:gameList){
				isExist=false;
				for(GameInfo game:gameInfos){
					if(game.getGameId().equals(gameInfo.getGameId())){
						isExist=true;
						break;
					}
				}
				if(!isExist){
					delGameIdList.add(gameInfo.getGameId());
				}
			}
			
			logger.info("-----saveGameInfoOnChange--changeList="+changeList.size()+" newList="+newList.size()+" delList="+delGameIdList);
			if(!CollectionUtils.isEmpty(newList)){
				 if (!gameInfoService.batchSave(newList)) {
                     logger.warn("batchSave gameInfos failed");
                 }
			}
			if(!CollectionUtils.isEmpty(changeList)){
				gameInfoService.updateBatch(changeList);
			}
			if(!CollectionUtils.isEmpty(delGameIdList)){
				gameInfoService.deleteByGameId(delGameIdList);
			}
			return true;
		} catch (Exception e) {
			logger.error("-----saveGameInfoOnChange--", e);
			return false;
		}
	}

}
