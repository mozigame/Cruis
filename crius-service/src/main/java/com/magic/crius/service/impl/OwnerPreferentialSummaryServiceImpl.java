package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.service.OwnerPreferentialSummaryService;
import com.magic.crius.storage.db.OwnerPreferentialSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public boolean save(OwnerPreferentialSummary record) {
        return ownerPreferentialSummaryDbService.save(record);
    }

    @Override
    public boolean checkExist(Long ownerId, Integer preferentialType, Integer pdate) {
        return ownerPreferentialSummaryDbService.checkExist(ownerId, preferentialType, pdate);
    }

    @Override
    public boolean updateSummary(OwnerPreferentialSummary summmary) {
        return ownerPreferentialSummaryDbService.updateSummary(summmary);
    }
}
