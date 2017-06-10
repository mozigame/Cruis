package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerReforwardDetail;
import com.magic.crius.service.OwnerReforwardDetailService;
import com.magic.crius.storage.db.OwnerReforwardDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 17:25
 */
@Service
public class OwnerReforwardDetailServiceImpl implements OwnerReforwardDetailService {

    @Resource
    private OwnerReforwardDetailDbService ownerReforwardDetailDbService;

    @Override
    public boolean insert(OwnerReforwardDetail detail) {
        return ownerReforwardDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerReforwardDetail> details) {
        return ownerReforwardDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerReforwardDetail detail) {
        return ownerReforwardDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerReforwardDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerReforwardDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
