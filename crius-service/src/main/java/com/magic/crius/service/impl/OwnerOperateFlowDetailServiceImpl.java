package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.service.OwnerOperateFlowDetailService;
import com.magic.crius.storage.db.OwnerOperateFlowDetailDbService;
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
public class OwnerOperateFlowDetailServiceImpl implements OwnerOperateFlowDetailService {

    @Resource
    private OwnerOperateFlowDetailDbService ownerOperateFlowDetailDbService;


    @Override
    public boolean insert(OwnerOperateFlowDetail detail) {
        return ownerOperateFlowDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowDetail> details) {
        return ownerOperateFlowDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerOperateFlowDetail detail) {
        return ownerOperateFlowDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerOperateFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
