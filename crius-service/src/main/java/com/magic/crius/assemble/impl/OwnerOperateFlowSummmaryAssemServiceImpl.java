package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateFlowSummmaryAssemService;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.service.OwnerOperateFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:21
 * 人工入款汇总
 */
@Service
public class OwnerOperateFlowSummmaryAssemServiceImpl implements OwnerOperateFlowSummmaryAssemService {

    @Resource
    private OwnerOperateFlowSummmaryService ownerOperateFlowSummmaryService;

    @Override
    public void batchSave(Map<String, OwnerOperateFlowSummmary> ownerOnlineFlowSummmaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (OwnerOperateFlowSummmary summmary : ownerOnlineFlowSummmaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = ownerOnlineFlowSummmaries.values().iterator().next().getPdate();
        List<OwnerOperateFlowSummmary> flowSummmaries = ownerOperateFlowSummmaryService.findByOwnerIds(ownerIds, pdate);
        List<OwnerOperateFlowSummmary> saves = new ArrayList<>();
        List<OwnerOperateFlowSummmary> updates = new ArrayList<>();

        Set<String> keys = ownerOnlineFlowSummmaries.keySet();
        for (OwnerOperateFlowSummmary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getOperateFlowType())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerOperateFlowSummmaryService.batchInsert(saves);
        for (OwnerOperateFlowSummmary summmary : updates) {
            ownerOperateFlowSummmaryService.updateSummary(summmary);
        }
    }
}
