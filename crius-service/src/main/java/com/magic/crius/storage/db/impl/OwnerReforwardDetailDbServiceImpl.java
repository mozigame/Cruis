package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerReforwardDetailMapper;
import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.storage.db.OwnerReforwardDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:22
 */
@Service
public class OwnerReforwardDetailDbServiceImpl implements OwnerReforwardDetailDbService {

    @Resource
    private OwnerReforwardDetailMapper ownerReforwardDetailMapper;


    @Override
    public boolean insert(OwnerReforwardDetail summmary) {
        return ownerReforwardDetailMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerReforwardDetail> summmaries) {
        return ownerReforwardDetailMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerReforwardDetail summmary) {
        return ownerReforwardDetailMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerReforwardDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerReforwardDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
