package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerReforwardMoneyToGameMapper;
import com.magic.crius.po.OwnerReforwardMoneyToGame;
import com.magic.crius.storage.db.OwnerReforwardMoneyToGameDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 19:30
 */
@Service
public class OwnerReforwardMoneyToGameDbServiceImpl implements OwnerReforwardMoneyToGameDbService {

    @Resource
    private OwnerReforwardMoneyToGameMapper ownerReforwardMoneyToGameMapper;

    @Override
    public boolean insert(OwnerReforwardMoneyToGame summmary) {
        return ownerReforwardMoneyToGameMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerReforwardMoneyToGame> summmaries) {
        return ownerReforwardMoneyToGameMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerReforwardMoneyToGame summmary) {
        return ownerReforwardMoneyToGameMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerReforwardMoneyToGame> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerReforwardMoneyToGameMapper.findByOwnerIds(ownerIds, pdate);
    }
}
