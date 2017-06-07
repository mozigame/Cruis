package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyAccountSummmaryService;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import com.magic.crius.storage.db.OwnerCompanyAccountSummmaryDbService;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service("ownerCompanyAccountSummmaryService")
public class OwnerCompanyAccountSummmaryServiceImpl implements OwnerCompanyAccountSummmaryService {

    @Resource
    private OwnerCompanyAccountSummmaryDbService ownerCompanyAccountSummmaryDbService;

    @Override
    public boolean insert(OwnerCompanyAccountSummmary summmary) {
        return ownerCompanyAccountSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyAccountSummmary> summmaries) {
        return ownerCompanyAccountSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerCompanyAccountSummmary summmary) {
        return ownerCompanyAccountSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerCompanyAccountSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyAccountSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}