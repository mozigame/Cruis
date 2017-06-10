package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerCompanyAccountDetailMapper;
import com.magic.crius.po.OwnerCompanyAccountDetail;
import com.magic.crius.storage.db.OwnerCompanyAccountDetailDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
@Service("ownerCompanyAccountDetailDbService")
public class OwnerCompanyAccountDetailDbServiceImpl implements OwnerCompanyAccountDetailDbService {

    @Autowired
    private OwnerCompanyAccountDetailMapper ownerCompanyAccountDetailMapper;


    @Override
    public boolean insert(OwnerCompanyAccountDetail flowDetail) {
        return ownerCompanyAccountDetailMapper.insert(flowDetail) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyAccountDetail> flowSummmaries) {
        return ownerCompanyAccountDetailMapper.batchInsert(flowSummmaries) > 0;
    }

    @Override
    public boolean updateDetail(OwnerCompanyAccountDetail flowDetail) {
        return ownerCompanyAccountDetailMapper.updateDetail(flowDetail) > 0;
    }

    @Override
    public List<OwnerCompanyAccountDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyAccountDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
