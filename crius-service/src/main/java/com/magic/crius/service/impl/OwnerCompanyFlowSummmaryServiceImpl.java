package com.magic.crius.service.impl;

import com.magic.crius.dao.db.OwnerCompanyFlowSummmaryMapper;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import com.magic.crius.storage.db.OwnerCompanyFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("ownerCompanyFlowSummmaryService")
public class OwnerCompanyFlowSummmaryServiceImpl implements OwnerCompanyFlowSummmaryService{

    @Resource
    private OwnerCompanyFlowSummmaryDbService ownerCompanyFlowSummmaryDbService;

    @Override
    public boolean save(OwnerCompanyFlowSummmary flowSummmary) {
        return ownerCompanyFlowSummmaryDbService.save(flowSummmary);
    }

    @Override
    public boolean updateSummary(OwnerCompanyFlowSummmary flowSummmary) {
        return ownerCompanyFlowSummmaryDbService.updateSummary(flowSummmary);
    }

    @Override
    public boolean checkExist(Long ownerId, Long accountNum, Integer pdate) {
        return ownerCompanyFlowSummmaryDbService.checkExist(ownerId, accountNum, pdate);
    }
}