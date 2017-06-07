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
        List<OwnerPreferentialSummary> saves = new ArrayList<>();
        List<OwnerPreferentialSummary> updates = new ArrayList<>();

        Set<String> keys = ownerPreferentialSummaries.keySet();
        for (OwnerPreferentialSummary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getPreferentialType())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerPreferentialSummaryService.batchInsert(saves);
        for (OwnerPreferentialSummary summmary : updates) {
            ownerPreferentialSummaryService.updateSummary(summmary);
        }
    }
}
