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
        Collection<OwnerCompanyFlowSummmary> saves = new ArrayList<>();
        List<OwnerCompanyFlowSummmary> updates = new ArrayList<>();
        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = ownerCompanyFlowSummmaries.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (OwnerCompanyFlowSummmary summmary : flowSummmaries) {
                    if (key.equals(summmary.getOwnerId() + "_" + summmary.getAccountNum())) {
                        flag = true;
                        flowSummmaries.remove(summmary);
                        break;
                    }
                }
                if (flag) {
                    updates.add(ownerCompanyFlowSummmaries.get(key));
                } else {
                    saves.add(ownerCompanyFlowSummmaries.get(key));
                }
            }
        } else {
            saves = ownerCompanyFlowSummmaries.values();
        }
        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = ownerCompanyFlowSummmaryService.batchInsert(saves);
        }
        for (OwnerCompanyFlowSummmary summmary : updates) {
            ownerCompanyFlowSummmaryService.updateSummary(summmary);
        }
    }
}