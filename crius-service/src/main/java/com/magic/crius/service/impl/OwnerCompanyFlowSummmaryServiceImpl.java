package com.magic.crius.service.impl;

import com.magic.crius.dao.crius.db.OwnerCompanyFlowSummmaryMapper;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service("ownerCompanyFlowSummmaryService")
public class OwnerCompanyFlowSummmaryServiceImpl implements OwnerCompanyFlowSummmaryService{

    @Resource
    private OwnerCompanyFlowSummmaryDbService ownerCompanyFlowSummmaryDbService;

    @Override
    public boolean insert(OwnerCompanyFlowSummmary summmary) {
        return ownerCompanyFlowSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyFlowSummmary> summmaries) {
        return ownerCompanyFlowSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerCompanyFlowSummmary summmary) {
        return ownerCompanyFlowSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerCompanyFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyFlowSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}