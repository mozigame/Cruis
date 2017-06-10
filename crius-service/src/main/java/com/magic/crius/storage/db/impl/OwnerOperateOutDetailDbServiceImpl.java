package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerOperateOutDetailMapper;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.storage.db.OwnerOperateOutDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 14:38
 */
@Service
public class OwnerOperateOutDetailDbServiceImpl implements OwnerOperateOutDetailDbService {


    @Resource
    private OwnerOperateOutDetailMapper ownerOperateOutDetailMapper;


    @Override
    public boolean insert(OwnerOperateOutDetail summmary) {
        return ownerOperateOutDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateOutDetail> summmaries) {
        return ownerOperateOutDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateDetail(OwnerOperateOutDetail summmary) {
        return ownerOperateOutDetailMapper.updateDetail(summmary) > 0;
    }

    @Override
    public List<OwnerOperateOutDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateOutDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
