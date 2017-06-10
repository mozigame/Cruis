package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerPreferentialDetail;
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
    public boolean insert(OwnerPreferentialDetail summmary) {
        return ownerPreferentialSummaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialDetail> summmaries) {
        return ownerPreferentialSummaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerPreferentialDetail summmary) {
        return ownerPreferentialSummaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerPreferentialDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialSummaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}
