package com.magic.crius.dao.crius.db;

import com.magic.api.commons.atlas.core.mybatis.MyBatisDaoImpl;
import com.magic.crius.po.GameInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
}