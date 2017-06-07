package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.service.OwnerPreferentialSummaryService;
import com.magic.crius.storage.db.OwnerPreferentialSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:31
 */
@Service
public class OwnerPreferentialSummaryServiceImpl implements OwnerPreferentialSummaryService {

    @Resource
    private OwnerPreferentialSummaryDbService ownerPreferentialSummaryDbService;


    @Override
    public boolean insert(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialSummary> summmaries) {
        return ownerPreferentialSummaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerPreferentialSummary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialSummaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}
