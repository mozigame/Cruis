package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerPreferentialSummaryMapper;
import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.storage.db.OwnerPreferentialSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:32
 */
@Service
public class OwnerPreferentialSummaryDbServiceImpl implements OwnerPreferentialSummaryDbService {

    @Resource
    private OwnerPreferentialSummaryMapper ownerPreferentialSummaryMapper;

    @Override
    public boolean insert(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialSummary> summmaries) {
        return ownerPreferentialSummaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerPreferentialSummary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialSummaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
