package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerOnlineFlowDetailMapper;
import com.magic.crius.po.OwnerOnlineFlowDetail;
import com.magic.crius.storage.db.OwnerOnlineFlowDetailDbService;
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
public class OwnerOnlineFlowDetailDbServiceImpl implements OwnerOnlineFlowDetailDbService {


    @Resource
    private OwnerOnlineFlowDetailMapper ownerOnlineFlowDetailMapper;

    @Override
    public boolean insert(OwnerOnlineFlowDetail summmary) {
        return ownerOnlineFlowDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOnlineFlowDetail> summmaries) {
        return ownerOnlineFlowDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateDetail(OwnerOnlineFlowDetail summmary) {
        return ownerOnlineFlowDetailMapper.updateDetail(summmary) > 0;
    }

    @Override
    public List<OwnerOnlineFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOnlineFlowDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
