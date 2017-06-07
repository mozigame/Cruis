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
    public boolean insert(OwnerReforwardDetail summmary) {
        return ownerReforwardDetailDbService.insert(summmary);
    }

    @Override
    public boolean batchInsert(Collection<OwnerReforwardDetail> summmaries) {
        return ownerReforwardDetailDbService.batchInsert(summmaries);
    }

    @Override
    public boolean updateSummary(OwnerReforwardDetail summmary) {
        return ownerReforwardDetailDbService.updateSummary(summmary);
    }

    @Override
    public List<OwnerReforwardDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerReforwardDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
