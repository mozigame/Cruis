package com.magic.crius.service.impl;

import com.magic.crius.po.ProxyBillSummary2game;
import com.magic.crius.service.ProxyBillSummary2gameService;
import com.magic.crius.storage.db.ProxyBillSummary2gameDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:40
 */
@Service
public class ProxyBillSummary2gameServiceImpl implements ProxyBillSummary2gameService {

    @Resource
    protected ProxyBillSummary2gameDbService proxyBillSummary2gameDbService;

    @Override
    public boolean save(ProxyBillSummary2game game) {
        return proxyBillSummary2gameDbService.save(game);
    }

    @Override
    public boolean batchInsert(List<ProxyBillSummary2game> gameList) {
        return proxyBillSummary2gameDbService.batchInsert(gameList) ;
    }
}
