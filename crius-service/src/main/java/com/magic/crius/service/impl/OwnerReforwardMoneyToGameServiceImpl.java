package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerReforwardMoneyToGame;
import com.magic.crius.service.OwnerReforwardMoneyToGameService;
import com.magic.crius.storage.db.OwnerReforwardMoneyToGameDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 19:28
 */

@Service
public class OwnerReforwardMoneyToGameServiceImpl implements OwnerReforwardMoneyToGameService {

    @Resource
    private OwnerReforwardMoneyToGameDbService ownerReforwardMoneyToGameDbService;

    @Override
    public boolean insert(OwnerReforwardMoneyToGame summmary) {
        return ownerReforwardMoneyToGameDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerReforwardMoneyToGame> summmaries) {
        return ownerReforwardMoneyToGameDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerReforwardMoneyToGame summmary) {
        return ownerReforwardMoneyToGameDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerReforwardMoneyToGame> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerReforwardMoneyToGameDbService.findByOwnerIds(ownerIds, pdate);
    }
}
