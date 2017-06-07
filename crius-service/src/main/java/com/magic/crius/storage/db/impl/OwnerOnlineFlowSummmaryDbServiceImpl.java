package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerOnlineFlowSummmaryMapper;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.storage.db.OwnerOnlineFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public boolean insert(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOnlineFlowSummmary> summmaries) {
        return ownerOnlineFlowSummmaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerOnlineFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOnlineFlowSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
