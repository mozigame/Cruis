package com.magic.crius.service.impl;

import com.magic.crius.po.GameInfo;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.storage.db.GameInfoDbService;
import com.magic.crius.storage.redis.GameInfoRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
}
