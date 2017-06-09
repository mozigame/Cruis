package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.OwnerInfoMapper;
import com.magic.crius.po.OwnerInfo;
import com.magic.crius.storage.db.OwnerInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:55
 */
@Service
public class OwnerInfoDbServiceImpl implements OwnerInfoDbService {

    @Resource
    private OwnerInfoMapper ownerInfoMapper;

    @Override
    public boolean insert(OwnerInfo record) {
        return ownerInfoMapper.insert(record) > 0;
    }

    @Override
    public boolean batchInsert(List<OwnerInfo> infos) {
        return ownerInfoMapper.batchInsert(infos) > 0;
    }

    @Override
    public OwnerInfo get(Long ownerId) {
        return ownerInfoMapper.get(ownerId);
    }
}
