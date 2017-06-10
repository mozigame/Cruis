package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerCompanyFlowDetailMapper;
import com.magic.crius.po.OwnerCompanyFlowDetail;
import com.magic.crius.storage.db.OwnerCompanyFlowDetailDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/31
 * Time: 13:41
 */
@Service("ownerCompanyFlowDetailDbService")
public class OwnerCompanyFlowDetailDbServiceImpl implements OwnerCompanyFlowDetailDbService {

    @Autowired
    private OwnerCompanyFlowDetailMapper ownerCompanyFlowDetailMapper;

    @Override
    public boolean insert(OwnerCompanyFlowDetail summmary) {
        return ownerCompanyFlowDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerCompanyFlowDetail> summmaries) {
        return ownerCompanyFlowDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateDetail(OwnerCompanyFlowDetail summmary) {
        return ownerCompanyFlowDetailMapper.updateDetail(summmary) > 0;
    }

    @Override
    public List<OwnerCompanyFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerCompanyFlowDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
