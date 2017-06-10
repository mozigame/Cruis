package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.service.OwnerCompanyAccountSummmaryService;
import com.magic.crius.storage.db.OwnerCompanyAccountSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service("ownerCompanyAccountSummmaryService")
public class OwnerCompanyAccountSummmaryServiceImpl implements OwnerCompanyAccountSummmaryService {

    @Resource
    private OwnerCompanyAccountSummmaryDbService ownerCompanyAccountSummmaryDbService;

    @Override
    public boolean insert(OwnerCompanyAccountDetail summmary) {
        return ownerCompanyAccountSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyAccountDetail> summmaries) {
        return ownerCompanyAccountSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerCompanyAccountDetail summmary) {
        return ownerCompanyAccountSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyAccountSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}