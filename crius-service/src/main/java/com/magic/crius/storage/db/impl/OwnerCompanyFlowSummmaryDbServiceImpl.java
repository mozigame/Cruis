package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerCompanyFlowSummmaryMapper;
import com.magic.crius.po.OwnerCompanyFlowDetail;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
@Service("ownerCompanyFlowSummmaryDbService")
public class OwnerCompanyFlowSummmaryDbServiceImpl implements OwnerCompanyFlowSummmaryDbService{

    @Autowired
    private OwnerCompanyFlowSummmaryMapper ownerCompanyFlowSummmaryMapper;

    @Override
    public boolean insert(OwnerCompanyFlowDetail summmary) {
        return ownerCompanyFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyFlowDetail> summmaries) {
        return ownerCompanyFlowSummmaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerCompanyFlowDetail summmary) {
        return ownerCompanyFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyFlowSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
