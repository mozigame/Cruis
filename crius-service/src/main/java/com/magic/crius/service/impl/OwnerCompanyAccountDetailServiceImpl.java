package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.service.OwnerCompanyAccountDetailService;
import com.magic.crius.storage.db.OwnerCompanyAccountDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service("ownerCompanyAccountDetailService")
public class OwnerCompanyAccountDetailServiceImpl implements OwnerCompanyAccountDetailService {

    @Resource
    private OwnerCompanyAccountDetailDbService ownerCompanyAccountDetailDbService;

    @Override
    public boolean insert(OwnerCompanyAccountDetail detail) {
        return ownerCompanyAccountDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyAccountDetail> details) {
        return ownerCompanyAccountDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerCompanyAccountDetail detail) {
        return ownerCompanyAccountDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyAccountDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}