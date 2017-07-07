package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.GameInfoMapper;
import com.magic.crius.po.GameInfo;
import com.magic.crius.storage.db.GameInfoDbService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:30
 */
@Service
public class GameInfoDbServiceImpl implements GameInfoDbService {

    private static final Logger logger = Logger.getLogger(GameInfoDbServiceImpl.class);

    @Resource(name = "tethysGameInfoMapper")
    private GameInfoMapper tethysGameInfoMapper;

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
        try {
            if (tethysGameInfoMapper.insertDelBatch(gameInfos)) {
                logger.warn("tethysGameInfoMapper insert gameInfos failed ");
            }
        } catch (Exception e) {
            logger.error("tethysGameInfoMapper insert gameInfos error ",e);
            e.printStackTrace();
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
}
