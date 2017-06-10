package com.magic.crius.service.impl;

import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.service.OwnerOperateOutDetailService;
import com.magic.crius.storage.db.OwnerOperateOutDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:00
 * 人工出款详情
 */
@Service
public class OwnerOperateOutDetailServiceImpl implements OwnerOperateOutDetailService {

    @Resource
    private OwnerOperateOutDetailDbService ownerOperateOutDetailDbService;

    @Override
    public boolean insert(OwnerOperateOutDetail detail) {
        return ownerOperateOutDetailDbService.insert(detail);
    }

    @Override
    public boolean batchInsert(Collection<OwnerOperateOutDetail> details) {
        return ownerOperateOutDetailDbService.batchInsert(details);
    }

    @Override
    public boolean updateDetail(OwnerOperateOutDetail detail) {
        return ownerOperateOutDetailDbService.updateDetail(detail);
    }

    @Override
    public List<OwnerOperateOutDetail> findByOwnerIds(Collection<Long> ownerIds, Integer pdate) {
        return ownerOperateOutDetailDbService.findByOwnerIds(ownerIds, pdate);
    }
}
