package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerOperateFlowSummmaryAssemService;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.service.OwnerOperateFlowSummmaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

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
    public void batchSave(Collection<OwnerOperateFlowSummmary> ownerOnlineFlowSummmaries) {
        for (OwnerOperateFlowSummmary summmary : ownerOnlineFlowSummmaries) {
            if (!ownerOperateFlowSummmaryService.checkExist(summmary.getOwnerId(), summmary.getOperateFlowType(), summmary.getPdate())) {
                if (!ownerOperateFlowSummmaryService.save(summmary)) {
                    //TODO
                }
            } else {
                if (!ownerOperateFlowSummmaryService.updateSummary(summmary)) {
                    //TODO
                }
            }
        }
    }
}
