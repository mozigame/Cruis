package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOperateFlowDetail;
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
    public boolean insert(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowSummmaryDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowDetail> summmaries) {
        return ownerOperateFlowSummmaryDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowSummmaryDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerOperateFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowSummmaryDbService.findByOwnerIds(ownerIds, pdate);
    }
}
