package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.service.OwnerPreferentialDetailService;
import com.magic.crius.storage.db.OwnerPreferentialDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/4
 * Time: 23:31
 */
@Service
public class OwnerPreferentialDetailServiceImpl implements OwnerPreferentialDetailService {

    @Resource
    private OwnerPreferentialDetailDbService ownerPreferentialDetailDbService;


    @Override
    public boolean insert(OwnerPreferentialDetail detail) {
        return ownerPreferentialDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialDetail> details) {
        return ownerPreferentialDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerPreferentialDetail detail) {
        return ownerPreferentialDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerPreferentialDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
