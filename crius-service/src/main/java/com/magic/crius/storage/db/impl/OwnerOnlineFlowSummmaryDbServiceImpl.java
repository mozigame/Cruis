package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerOnlineFlowSummmaryMapper;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.storage.db.OwnerOnlineFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 16:59
 */
@Service
public class OwnerOnlineFlowSummmaryDbServiceImpl implements OwnerOnlineFlowSummmaryDbService {


    @Resource
    private OwnerOnlineFlowSummmaryMapper ownerOnlineFlowSummmaryMapper;

    @Override
    public boolean save(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Long merchantCode, Integer pdate) {
        return ownerOnlineFlowSummmaryMapper.checkExist(ownerId, merchantCode, pdate) > 0;
    }
}
