package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOnlineFlowSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OwnerOnlineFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 17:18
 */
@Service
public class OwnerOnlineFlowSummmaryAssemServiceImpl implements OwnerOnlineFlowSummmaryAssemService {

    @Resource
    private OwnerOnlineFlowSummmaryService ownerOnlineFlowSummmaryService;

    @Override
    public void batchSave(Map<String, OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (OwnerOnlineFlowSummmary summmary : ownerOnlineFlowSummmaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = ownerOnlineFlowSummmaries.values().iterator().next().getPdate();
        List<OwnerOnlineFlowSummmary> flowSummmaries = ownerOnlineFlowSummmaryService.findByOwnerIds(ownerIds, pdate);
        List<OwnerOnlineFlowSummmary> saves = new ArrayList<>();
        List<OwnerOnlineFlowSummmary> updates = new ArrayList<>();

        Set<String> keys = ownerOnlineFlowSummmaries.keySet();
        for (OwnerOnlineFlowSummmary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getMerchantCode())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerOnlineFlowSummmaryService.batchInsert(saves);
        for (OwnerOnlineFlowSummmary summmary : updates) {
            ownerOnlineFlowSummmaryService.updateSummary(summmary);
        }
    }
}
