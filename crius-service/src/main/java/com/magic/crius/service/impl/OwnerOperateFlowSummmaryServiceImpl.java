package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import com.magic.crius.service.OwnerOperateFlowSummmaryService;
import com.magic.crius.storage.db.OwnerOperateFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public boolean insert(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowSummmary> summmaries) {
        return ownerOperateFlowSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowSummmary summmary) {
        return ownerOperateFlowSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerOperateFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}
