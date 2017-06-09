package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerPreferentialSummaryAssemService;
import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.service.OwnerPreferentialSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:36
 */
@Service
public class OwnerPreferentialSummaryAssemServiceImpl implements OwnerPreferentialSummaryAssemService {

    @Resource
    private OwnerPreferentialSummaryService ownerPreferentialSummaryService;

    @Override
    public void batchSave(Map<String, OwnerPreferentialSummary> ownerPreferentialSummaries) {

        Set<Long> ownerIds = new HashSet<>();
        for (OwnerPreferentialSummary summmary : ownerPreferentialSummaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = ownerPreferentialSummaries.values().iterator().next().getPdate();
        List<OwnerPreferentialSummary> flowSummmaries = ownerPreferentialSummaryService.findByOwnerIds(ownerIds, pdate);
        Collection<OwnerPreferentialSummary> saves = new ArrayList<>();
        List<OwnerPreferentialSummary> updates = new ArrayList<>();
        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = ownerPreferentialSummaries.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (OwnerPreferentialSummary summmary : flowSummmaries) {
                    if (key.equals(summmary.getOwnerId() + "_" + summmary.getPreferentialType())) {
                        flag = true;
                        flowSummmaries.remove(summmary);
                        break;
                    }
                }
                if (flag) {
                    updates.add(ownerPreferentialSummaries.get(key));
                } else {
                    saves.add(ownerPreferentialSummaries.get(key));
                }
            }
        } else {
            saves = ownerPreferentialSummaries.values();
        }
        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = ownerPreferentialSummaryService.batchInsert(saves);
        }
        for (OwnerPreferentialSummary summmary : updates) {
            ownerPreferentialSummaryService.updateSummary(summmary);
        }
    }
}
