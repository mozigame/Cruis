package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerCompanyAccountSummmaryMapper;
import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.storage.db.OwnerCompanyAccountSummmaryDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public boolean insert(OwnerCompanyAccountDetail flowSummmary) {
        return ownerCompanyAccountSummmaryMapper.insert(flowSummmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyAccountDetail> flowSummmaries) {
        return ownerCompanyAccountSummmaryMapper.batchInsert(flowSummmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerCompanyAccountDetail flowSummmary) {
        return ownerCompanyAccountSummmaryMapper.updateSummary(flowSummmary) > 0;
    }

    @Override
    public List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyAccountSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
