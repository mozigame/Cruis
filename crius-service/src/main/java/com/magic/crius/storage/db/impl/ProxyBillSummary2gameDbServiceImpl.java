package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.ProxyBillSummary2gameMapper;
import com.magic.crius.po.ProxyBillSummary2game;
import com.magic.crius.storage.db.ProxyBillSummary2gameDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:38
 */
@Service
public class ProxyBillSummary2gameDbServiceImpl implements ProxyBillSummary2gameDbService {

    @Resource
    private ProxyBillSummary2gameMapper proxyBillSummary2gameMapper;

    @Override
    public boolean save(ProxyBillSummary2game game) {
        return proxyBillSummary2gameMapper.insert(game) > 0;
    }

    @Override
    public boolean batchInsert(List<ProxyBillSummary2game> gameList) {
        return proxyBillSummary2gameMapper.batchInsert(gameList) > 0;
    }
}
