package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerReforwardDetailAssemService;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.service.OwnerReforwardDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:27
 */
@Service
public class OwnerReforwardDetailAssemServiceImpl implements OwnerReforwardDetailAssemService {

    @Resource
    private OwnerReforwardDetailService ownerReforwardDetailService;

    @Override
    public void batchSave(Map<String, OwnerReforwardDetail> userOutMoneySummaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (OwnerReforwardDetail summmary : userOutMoneySummaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = userOutMoneySummaries.values().iterator().next().getPdate();
        List<OwnerReforwardDetail> flowSummmaries = ownerReforwardDetailService.findByOwnerIds(ownerIds, pdate);
        List<OwnerReforwardDetail> saves = new ArrayList<>();
        List<OwnerReforwardDetail> updates = new ArrayList<>();

        Set<String> keys = userOutMoneySummaries.keySet();
        for (OwnerReforwardDetail summmary : flowSummmaries) {
            if (!keys.contains(summmary.getOwnerId() + "_" + summmary.getGameType())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerReforwardDetailService.batchInsert(saves);
        for (OwnerReforwardDetail summmary : updates) {
            ownerReforwardDetailService.updateSummary(summmary);
        }
    }
}
