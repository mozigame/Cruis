package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyFlowSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 公司入款明细
 */
@Service("ownerCompanyFlowSummmaryAssemService")
public class OwnerCompanyFlowSummmaryAssemServiceImpl implements OwnerCompanyFlowSummmaryAssemService {

    @Resource
    private OwnerCompanyFlowSummmaryService ownerCompanyFlowSummmaryService;

    @Override
    public void batchSave(Map<String, OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (OwnerCompanyFlowSummmary summmary : ownerCompanyFlowSummmaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = ownerCompanyFlowSummmaries.values().iterator().next().getPdate();
        List<OwnerCompanyFlowSummmary> flowSummmaries = ownerCompanyFlowSummmaryService.findByOwnerIds(ownerIds, pdate);
        List<OwnerCompanyFlowSummmary> saves = new ArrayList<>();
        List<OwnerCompanyFlowSummmary> updates = new ArrayList<>();

        Set<String> keys = ownerCompanyFlowSummmaries.keySet();
        for (OwnerCompanyFlowSummmary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getAccountNum())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerCompanyFlowSummmaryService.batchInsert(saves);
        for (OwnerCompanyFlowSummmary summmary : updates) {
            ownerCompanyFlowSummmaryService.updateSummary(summmary);
        }
    }
}