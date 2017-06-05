package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerOperateFlowSummmaryMapper;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.storage.db.OwnerOperateFlowSummmaryDbService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 18:09
 */
@Service
public class OwnerOperateFlowSummmaryDbServiceImpl implements OwnerOperateFlowSummmaryDbService {

    @Resource
    private OwnerOperateFlowSummmaryMapper ownerOperateFlowSummmaryMapper;

    @Override
    public boolean save(OwnerOperateFlowSummmary record) {
        return ownerOperateFlowSummmaryMapper.insert(record) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Integer operateFlowType, Integer pdate) {
        return ownerOperateFlowSummmaryMapper.checkExist(ownerId, operateFlowType, pdate) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryMapper.updateSummary(summmary) > 0;
    }
}
