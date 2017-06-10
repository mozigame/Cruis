package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerPreferentialSummaryMapper;
import com.magic.crius.po.OwnerPreferentialDetail;
import com.magic.crius.storage.db.OwnerPreferentialSummaryDbService;
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
public class OwnerPreferentialSummaryDbServiceImpl implements OwnerPreferentialSummaryDbService {

    @Resource
    private OwnerPreferentialSummaryMapper ownerPreferentialSummaryMapper;

    @Override
    public boolean insert(OwnerPreferentialDetail summmary) {
        return ownerPreferentialSummaryMapper.insert(summmary) > 0;
    }

    @Override
    public boolean batchInsert(Collection<OwnerPreferentialDetail> summmaries) {
        return ownerPreferentialSummaryMapper.batchInsert(summmaries) > 0;
    }

    @Override
    public boolean updateSummary(OwnerPreferentialDetail summmary) {
        return ownerPreferentialSummaryMapper.updateSummary(summmary) > 0;
    }

    @Override
    public List<OwnerPreferentialDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerPreferentialSummaryMapper.findByOwnerIds(ownerIds, pdate);
    }
}
