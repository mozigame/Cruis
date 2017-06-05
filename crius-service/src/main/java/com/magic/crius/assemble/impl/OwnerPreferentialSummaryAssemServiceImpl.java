package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerPreferentialSummaryAssemService;
import com.magic.crius.po.OwnerPreferentialSummary;
import com.magic.crius.service.OwnerPreferentialSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

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
    public void batchSave(Collection<OwnerPreferentialSummary> ownerPreferentialSummaries) {
        for (OwnerPreferentialSummary summmary : ownerPreferentialSummaries) {
            if (!ownerPreferentialSummaryService.checkExist(summmary.getOwnerId(), summmary.getPreferentialType(), summmary.getPdate())) {
                if (!ownerPreferentialSummaryService.save(summmary)) {
                    //TODO
                }
            } else {
                if (!ownerPreferentialSummaryService.updateSummary(summmary)) {
                    //TODO
                }
            }
        }
    }
}
