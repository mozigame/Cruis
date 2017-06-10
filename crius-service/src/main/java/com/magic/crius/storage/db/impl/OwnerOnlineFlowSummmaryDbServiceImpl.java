package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerOnlineFlowSummmaryMapper;
import com.magic.crius.po.OwnerOnlineFlowDetail;
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
    public boolean insert(OwnerOnlineFlowDetail summmary) {
        return ownerOnlineFlowSummmaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOnlineFlowDetail> summmaries) {
        return ownerOnlineFlowSummmaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOnlineFlowDetail summmary) {
        return ownerOnlineFlowSummmaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerOnlineFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOnlineFlowSummmaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
