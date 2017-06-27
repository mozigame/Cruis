package com.magic.crius.dao.tethys.db;

import com.magic.api.commons.atlas.core.mybatis.MyBatisDaoImpl;
import com.magic.crius.po.GameInfo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * tethys 中的gameInfo
 */
@Component("tethysGameInfoMapper")
public class GameInfoMapper extends MyBatisDaoImpl<GameInfo, Long> {

    public Long insertBatch(List<GameInfo> gameInfos) {
        if (gameInfos != null && gameInfos.size() > 0) {
            for (Map.Entry<Integer, SqlSession> entry : super.shardSqlSessionTemplates.entrySet()) {
                int num = entry.getValue().insert(sqlMapNamespace + "." + POSTFIX_INSERT_BATCH, gameInfos);
                if (num < gameInfos.size()) {
                    System.out.println("insert gameInfos failed, size : " + num);
                }
            }
            return (long) gameInfos.size();
        }
        return 0L;
    }

    public boolean deleteAll() {
        for (Map.Entry<Integer, SqlSession> entry : super.shardSqlSessionTemplates.entrySet()) {
            int num = entry.getValue().delete(sqlMapNamespace + "." + POSTFIX_DELETE);
        }
        return true;
    }
}