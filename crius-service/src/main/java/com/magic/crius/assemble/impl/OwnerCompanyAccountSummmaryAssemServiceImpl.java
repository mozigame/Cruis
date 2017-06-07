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
        List<OwnerCompanyAccountSummmary> saves = new ArrayList<>();
        List<OwnerCompanyAccountSummmary> updates = new ArrayList<>();
        for (OwnerCompanyAccountSummmary summary : summmaries) {
            if (!ownerIds.contains(summary.getOwnerId())) {
                saves.add(summary);
            } else {
                updates.add(summary);
            }
        }
        //todo 错误处理
        boolean saveResult = ownerCompanyAccountSummmaryService.batchInsert(saves);
        for (OwnerCompanyAccountSummmary summmary : updates) {
            ownerCompanyAccountSummmaryService.updateSummary(summmary);
        }
    }
}
