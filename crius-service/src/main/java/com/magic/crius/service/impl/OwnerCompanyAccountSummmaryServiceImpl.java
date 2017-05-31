package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyAccountSummmaryService;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import com.magic.crius.storage.db.OwnerCompanyAccountSummmaryDbService;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("ownerCompanyAccountSummmaryService")
public class OwnerCompanyAccountSummmaryServiceImpl implements OwnerCompanyAccountSummmaryService {

    @Resource
    private OwnerCompanyAccountSummmaryDbService ownerCompanyAccountSummmaryDbService;

    @Override
    public boolean save(OwnerCompanyAccountSummmary accountSummmary) {
        return ownerCompanyAccountSummmaryDbService.save(accountSummmary);
    }

    @Override
    public boolean updateSummary(OwnerCompanyAccountSummmary accountSummmary) {
        return ownerCompanyAccountSummmaryDbService.updateSummary(accountSummmary);
    }

    @Override
    public boolean checkExist(Long ownerId, Integer summaryType, Integer pdate) {
        return ownerCompanyAccountSummmaryDbService.checkExist(ownerId, summaryType, pdate);
    }
}