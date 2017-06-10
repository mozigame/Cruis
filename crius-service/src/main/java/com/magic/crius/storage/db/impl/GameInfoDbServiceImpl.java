package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.GameInfoMapper;
import com.magic.crius.po.GameInfo;
import com.magic.crius.storage.db.GameInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:30
 */
@Service
public class GameInfoDbServiceImpl implements GameInfoDbService {

    @Resource
    private GameInfoMapper gameInfoMapper;

    @Override
    public boolean batchSave(Collection<GameInfo> gameInfos) {
        try {
            return gameInfoMapper.batchInsert(gameInfos) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
