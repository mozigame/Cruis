package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyFlowSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service("ownerCompanyFlowSummmaryAssemService")
public class OwnerCompanyFlowSummmaryAssemServiceImpl implements OwnerCompanyFlowSummmaryAssemService{

    @Resource
    private OwnerCompanyFlowSummmaryService ownerCompanyFlowSummmaryService;

    @Override
    public void batchSave(Collection<OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaries) {
        for (OwnerCompanyFlowSummmary flowSummmary : ownerCompanyFlowSummmaries) {
            if (ownerCompanyFlowSummmaryService.checkExist(flowSummmary.getOwnerId(), flowSummmary.getAccountNum(), flowSummmary.getPdate())) {
                if (!ownerCompanyFlowSummmaryService.updateSummary(flowSummmary)) {
                    //TODO
                }
            } else {
                if (!ownerCompanyFlowSummmaryService.save(flowSummmary)) {
                    //TODO
                }
            }
        }
    }
}