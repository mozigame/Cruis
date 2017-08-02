package com.magic.crius.dao.tethys.db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.magic.api.commons.atlas.core.mybatis.MyBatisDaoImpl;
import com.magic.crius.po.GameInfo;

/**
 * tethys 中的gameInfo
 */
@Component("tethysGameInfoMapper")
public class GameInfoMapper extends MyBatisDaoImpl<GameInfo, Long> {

    private static final Logger logger = Logger.getLogger(GameInfoMapper.class);

    public Long insertBatch(List<GameInfo> gameInfos) {
        if (gameInfos != null && gameInfos.size() > 0) {
            for (Map.Entry<Integer, SqlSession> entry : super.shardSqlSessionTemplates.entrySet()) {
            	int num = entry.getValue().insert(sqlMapNamespace + "." + POSTFIX_INSERT_BATCH, gameInfos);
                logger.info("tethys batch insert gameInfo, num : "+ num +" , key : " + entry.getKey());

            }
            return (long) gameInfos.size();
        }
        return 0L;
    }
    
    /**
     * delete用于支持删除全部数据
     */
    @Override
    public int delete(Long pk){
    	if(pk!=null){
    		try {
				return super.delete(pk);
			} catch (Exception e) {
				logger.error("-----tethys-delete--pk:"+pk, e);
				return 0;
			}
    	}
    	 for (Map.Entry<Integer, SqlSession> entry : super.shardSqlSessionTemplates.entrySet()) {
             try {
                 int num = entry.getValue().delete(sqlMapNamespace + "." + POSTFIX_DELETE);
                 logger.info("tethys delete all gameInfo ,num : " + num + ", key:" + entry.getKey());
             } catch (Exception e) {
                 logger.error("-----tethys-deleteAll--key:"+entry.getKey(), e);
             }
         }
    	 return 1;
    }

    public boolean insertDelBatch(List<GameInfo> gameInfos) {
        for (Map.Entry<Integer, SqlSession> entry : super.shardSqlSessionTemplates.entrySet()) {
            try {
                int num = entry.getValue().delete(sqlMapNamespace + "." + POSTFIX_DELETE);
                logger.info("tethys delete all gameInfo ,num : " + num + ", key:" + entry.getKey());
            } catch (Exception e) {
                e.printStackTrace();
            }

            int num = entry.getValue().insert(sqlMapNamespace + "." + POSTFIX_INSERT_BATCH, gameInfos);
            logger.info("tethys batch insert gameInfo, num : "+ num +" , key : " + entry.getKey());
        }
        return true;
    }




}