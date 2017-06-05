package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerCompanyFlowSummmaryMapper;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean save(OwnerCompanyFlowSummmary flowSummmary) {
        return ownerCompanyFlowSummmaryMapper.insert(flowSummmary) > 0;
    }

    @Override
    public boolean updateSummary(OwnerCompanyFlowSummmary flowSummmary) {
        return ownerCompanyFlowSummmaryMapper.updateSummary(flowSummmary) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Long accountNum, Integer pdate) {
        return ownerCompanyFlowSummmaryMapper.checkExist(ownerId, accountNum, pdate) > 0;
    }
}
