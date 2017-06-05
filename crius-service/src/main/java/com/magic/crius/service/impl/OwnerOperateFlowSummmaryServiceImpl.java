package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.service.OwnerOperateFlowSummmaryService;
import com.magic.crius.storage.db.OwnerOperateFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:14
 */
@Service
public class OwnerOperateFlowSummmaryServiceImpl implements OwnerOperateFlowSummmaryService {

    @Resource
    private OwnerOperateFlowSummmaryDbService ownerOperateFlowSummmaryDbService;

    @Override
    public boolean save(OwnerOperateFlowSummmary record) {
        return ownerOperateFlowSummmaryDbService.save(record);
    }

    @Override
    public boolean checkExist(Long ownerId, Integer operateFlowType, Integer pdate) {
        return ownerOperateFlowSummmaryDbService.checkExist(ownerId, operateFlowType, pdate);
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryDbService.updateSummary(summmary);
    }
}
