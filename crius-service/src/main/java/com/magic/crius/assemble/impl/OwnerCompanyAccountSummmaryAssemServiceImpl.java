package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerCompanyAccountSummmaryAssemService;
import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.OwnerCompanyAccountSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:52
 * 公司账目汇总
 */
@Service("ownerCompanyAccountSummmaryAssemService")
public class OwnerCompanyAccountSummmaryAssemServiceImpl implements OwnerCompanyAccountSummmaryAssemService {

    @Resource
    private OwnerCompanyAccountSummmaryService ownerCompanyAccountSummmaryService;

    @Override
    public void batchSave(Map<Long, OwnerCompanyAccountSummmary> ownerCompanyAccountSummmaries) {
        Set<Long> ownerIds = ownerCompanyAccountSummmaries.keySet();

        int pdate = ownerCompanyAccountSummmaries.values().iterator().next().getPdate();
        List<OwnerCompanyAccountSummmary> summmaries = ownerCompanyAccountSummmaryService.findByOwnerIds(ownerIds, pdate);
        Collection<OwnerCompanyAccountSummmary> saves = new ArrayList<>();
        List<OwnerCompanyAccountSummmary> updates = new ArrayList<>();
        if (summmaries != null && summmaries.size() > 0) {
            for (Long key : ownerIds) {
                boolean flag = false;
                for (OwnerCompanyAccountSummmary summmary : summmaries) {
                    if (key.longValue() == summmary.getOwnerId().longValue()) {
                        flag = true;
                        summmaries.remove(summmary);
                        break;
                    }
                }
                if (flag) {
                    updates.add(ownerCompanyAccountSummmaries.get(key));
                } else {
                    saves.add(ownerCompanyAccountSummmaries.get(key));
                }
            }
        } else {
            saves = ownerCompanyAccountSummmaries.values();
        }
        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = ownerCompanyAccountSummmaryService.batchInsert(saves);
        }
        for (OwnerCompanyAccountSummmary summmary : updates) {
            ownerCompanyAccountSummmaryService.updateSummary(summmary);
        }
    }
}
