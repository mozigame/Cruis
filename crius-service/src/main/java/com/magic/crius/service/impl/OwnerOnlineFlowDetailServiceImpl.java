package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOnlineFlowDetail;
import com.magic.crius.service.OwnerOnlineFlowDetailService;
import com.magic.crius.storage.db.OwnerOnlineFlowDetailDbService;
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
public class OwnerOnlineFlowDetailServiceImpl implements OwnerOnlineFlowDetailService {

    @Resource
    private OwnerOnlineFlowDetailDbService ownerOnlineFlowDetailDbService;

    @Override
    public boolean insert(OwnerOnlineFlowDetail detail) {
        return ownerOnlineFlowDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOnlineFlowDetail> details) {
        return ownerOnlineFlowDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerOnlineFlowDetail detail) {
        return ownerOnlineFlowDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerOnlineFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOnlineFlowDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
