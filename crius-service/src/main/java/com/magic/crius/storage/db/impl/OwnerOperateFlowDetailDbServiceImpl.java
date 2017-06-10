package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerOperateFlowDetailMapper;
import com.magic.crius.po.OwnerOperateFlowDetail;
import com.magic.crius.storage.db.OwnerOperateFlowDetailDbService;
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
public class OwnerOperateFlowDetailDbServiceImpl implements OwnerOperateFlowDetailDbService {

    @Resource
    private OwnerOperateFlowDetailMapper ownerOperateFlowDetailMapper;


    @Override
    public boolean insert(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateFlowDetail> summmaries) {
        return ownerOperateFlowDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateDetail(OwnerOperateFlowDetail summmary) {
        return ownerOperateFlowDetailMapper.updateDetail(summmary) > 0;
    }

    @Override
    public List<OwnerOperateFlowDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateFlowDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}


