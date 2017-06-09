package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.service.OwnerOperateOutDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:02
 */
@Service
public class OwnerOperateOutDetailAssemServiceImpl implements OwnerOperateOutDetailAssemService {

    @Resource
    private OwnerOperateOutDetailService ownerOperateOutDetailService;

    @Override
    public void batchSave(Map<String, OwnerOperateOutDetail> ownerOperateOutDetails) {


        Set<Long> ownerIds = new HashSet<>();
        for (OwnerOperateOutDetail summmary : ownerOperateOutDetails.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = ownerOperateOutDetails.values().iterator().next().getPdate();
        List<OwnerOperateOutDetail> flowSummmaries = ownerOperateOutDetailService.findByOwnerIds(ownerIds, pdate);
        Collection<OwnerOperateOutDetail> saves = new ArrayList<>();
        List<OwnerOperateOutDetail> updates = new ArrayList<>();


        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = ownerOperateOutDetails.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (OwnerOperateOutDetail summmary : flowSummmaries) {
                    if (key.equals(summmary.getOwnerId() + "_" + summmary.getOperateOutType())) {
                        flag = true;
                        flowSummmaries.remove(summmary);
                        break;
                    }
                }
                if (flag) {
                    updates.add(ownerOperateOutDetails.get(key));
                } else {
                    saves.add(ownerOperateOutDetails.get(key));
                }
            }
        } else {
            saves = ownerOperateOutDetails.values();
        }
        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = ownerOperateOutDetailService.batchInsert(saves);
        }
        for (OwnerOperateOutDetail summmary : updates) {
            ownerOperateOutDetailService.updateSummary(summmary);
        }
    }
}
