package com.magic.crius.service.impl;

import com.magic.crius.dao.crius.db.OwnerOnlineFlowSummmaryMapper;
import com.magic.crius.po.OwnerOnlineFlowSummmary;
import com.magic.crius.service.OwnerOnlineFlowSummmaryService;
import com.magic.crius.storage.db.OwnerOnlineFlowSummmaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public boolean insert(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOnlineFlowSummmary> summmaries) {
        return ownerOnlineFlowSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerOnlineFlowSummmary summmary) {
        return ownerOnlineFlowSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerOnlineFlowSummmary> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOnlineFlowSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}
