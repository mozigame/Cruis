package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerCompanyFlowDetail;
import com.magic.crius.service.OwnerCompanyFlowDetailService;
import com.magic.crius.storage.db.OwnerCompanyFlowDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service("ownerCompanyFlowDetailService")
public class OwnerCompanyFlowDetailServiceImpl implements OwnerCompanyFlowDetailService {

    @Resource
    private OwnerCompanyFlowDetailDbService ownerCompanyFlowDetailDbService;

    @Override
    public boolean insert(OwnerCompanyFlowDetail detail) {
        return ownerCompanyFlowDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyFlowDetail> details) {
        return ownerCompanyFlowDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerCompanyFlowDetail detail) {
        return ownerCompanyFlowDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyFlowDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}