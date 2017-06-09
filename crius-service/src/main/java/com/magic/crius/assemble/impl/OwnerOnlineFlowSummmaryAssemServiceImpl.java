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
        Collection<OwnerOnlineFlowSummmary> saves = new ArrayList<>();
        List<OwnerOnlineFlowSummmary> updates = new ArrayList<>();

        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = ownerOnlineFlowSummmaries.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (OwnerOnlineFlowSummmary summmary : flowSummmaries) {
                    if (key.equals(summmary.getOwnerId() + "_" + summmary.getMerchantCode())) {
                        flag  = true;
                        flowSummmaries.remove(summmary);
                        break;
                    }
                }
                if (flag) {
                    updates.add(ownerOnlineFlowSummmaries.get(key));
                } else {
                    saves.add(ownerOnlineFlowSummmaries.get(key));
                }
            }
        } else {
            saves = ownerOnlineFlowSummmaries.values();
        }



        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = ownerOnlineFlowSummmaryService.batchInsert(saves);
        }
        for (OwnerOnlineFlowSummmary summmary : updates) {
            ownerOnlineFlowSummmaryService.updateSummary(summmary);
        }
    }
}
