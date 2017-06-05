package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOnlineFlowSummmaryAssemService;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OwnerOnlineFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

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
    public void batchSave(Collection<OwnerOnlineFlowSummmary> ownerOnlineFlowSummmaries) {
        for (OwnerOnlineFlowSummmary summmary : ownerOnlineFlowSummmaries) {
            if (!ownerOnlineFlowSummmaryService.checkExist(summmary.getOwnerId(), summmary.getMerchantCode(), summmary.getPdate())) {
                if (!ownerOnlineFlowSummmaryService.save(summmary)) {
                    //TODO
                }
            } else {
                if (!ownerOnlineFlowSummmaryService.updateSummary(summmary)) {
                    //TODO
                }
            }
        }
    }
}
