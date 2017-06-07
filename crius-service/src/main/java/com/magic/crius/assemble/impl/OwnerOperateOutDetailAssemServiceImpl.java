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
        List<OwnerOperateOutDetail> saves = new ArrayList<>();
        List<OwnerOperateOutDetail> updates = new ArrayList<>();

        Set<String> keys = ownerOperateOutDetails.keySet();
        for (OwnerOperateOutDetail summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getOperateOutType())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerOperateOutDetailService.batchInsert(saves);
        for (OwnerOperateOutDetail summmary : updates) {
            ownerOperateOutDetailService.updateSummary(summmary);
        }
    }
}
