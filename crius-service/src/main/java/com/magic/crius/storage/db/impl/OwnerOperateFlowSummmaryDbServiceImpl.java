package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerOperateFlowSummmaryMapper;
import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.storage.db.OwnerOperateFlowSummmaryDbService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public boolean insert(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowSummmary> summmaries) {
        return ownerOperateFlowSummmaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerOperateFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}


