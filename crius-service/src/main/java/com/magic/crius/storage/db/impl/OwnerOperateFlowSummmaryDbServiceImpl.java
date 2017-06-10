package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerOperateFlowSummmaryMapper;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.storage.db.OwnerOperateFlowSummmaryDbService;
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
    public boolean insert(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowDetail> summmaries) {
        return ownerOperateFlowSummmaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerOperateFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}


