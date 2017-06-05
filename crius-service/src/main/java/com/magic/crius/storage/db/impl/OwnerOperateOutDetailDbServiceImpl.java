package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerOperateOutDetailMapper;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.storage.db.OwnerOperateOutDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public boolean save(OwnerOperateOutDetail detail) {
        return ownerOperateOutDetailMapper.insert(detail) > 0;
    }

    @Override
    public boolean checkExist(Long ownerId, Integer operateOutType, Integer pdate) {
        return ownerOperateOutDetailMapper.checkExist(ownerId, operateOutType, pdate) > 0;
    }

    @Override
    public boolean updateSummary(OwnerOperateOutDetail detail) {
        return ownerOperateOutDetailMapper.updateSummary(detail) > 0;
    }
}
