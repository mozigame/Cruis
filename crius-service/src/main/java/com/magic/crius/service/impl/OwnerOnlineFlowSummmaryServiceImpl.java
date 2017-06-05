package com.magic.crius.service.impl;

import com.magic.crius.dao.db.OwnerOnlineFlowSummmaryMapper;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OwnerOnlineFlowSummmaryService;
import com.magic.crius.storage.db.OwnerOnlineFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:17
 */
@Service
public class OwnerOnlineFlowSummmaryServiceImpl implements OwnerOnlineFlowSummmaryService {

    @Resource
    private OwnerOnlineFlowSummmaryDbService ownerOnlineFlowSummmaryDbService;

    @Override
    public boolean save(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryDbService.save(summmary);
    }

    @Override
    public boolean updateSummary(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public boolean checkExist(Long ownerId, Long merchantCode, Integer pdate) {
        return ownerOnlineFlowSummmaryDbService.checkExist(ownerId, merchantCode, pdate);
    }
}
