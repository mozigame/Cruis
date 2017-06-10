package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerPreferentialDetailMapper;
import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.storage.db.OwnerPreferentialDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:32
 */
@Service
public class OwnerPreferentialDetailDbServiceImpl implements OwnerPreferentialDetailDbService {

    @Resource
    private OwnerPreferentialDetailMapper ownerPreferentialDetailMapper;

    @Override
    public boolean insert(OwnerPreferentialDetail detail) {
        return ownerPreferentialDetailMapper.insert(detail) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialDetail> details) {
        return ownerPreferentialDetailMapper.batchInsert(details) > 0;
    }

    @Override
    public boolean updateDetail(OwnerPreferentialDetail detail) {
        return ownerPreferentialDetailMapper.updateDetail(detail) > 0;
    }

    @Override
    public List<OwnerPreferentialDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialDetailMapper.findByOwnerIds(ownerIds, pdate);
    }
}
