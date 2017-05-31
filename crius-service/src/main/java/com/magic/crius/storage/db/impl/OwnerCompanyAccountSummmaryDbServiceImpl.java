package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerCompanyAccountSummmaryMapper;
import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.storage.db.OwnerCompanyAccountSummmaryDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
@Service("ownerCompanyAccountSummmaryDbService")
public class OwnerCompanyAccountSummmaryDbServiceImpl implements OwnerCompanyAccountSummmaryDbService{

    @Autowired
    private OwnerCompanyAccountSummmaryMapper ownerCompanyAccountSummmaryMapper;

    @Override
    public boolean save(OwnerCompanyAccountSummmary accountSummmary) {
        return ownerCompanyAccountSummmaryMapper.save(accountSummmary) > 0;
    }

    @Override
    public boolean updateSummary(OwnerCompanyAccountSummmary accountSummmary) {
        return ownerCompanyAccountSummmaryMapper.updateSummary(accountSummmary);
    }

    @Override
    public boolean checkExist(Long ownerId, Integer summaryType, Integer pdate) {
        return ownerCompanyAccountSummmaryMapper.checkExist(ownerId, summaryType, pdate) > 0;
    }
}
