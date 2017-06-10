package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.OwnerAwardDetailMapper;
import com.magic.crius.po.OwnerAwardDetail;
import com.magic.crius.storage.db.OwnerAwardDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:32
 */
@Service
public class OwnerAwardDetailDbServiceImpl implements OwnerAwardDetailDbService {

    @Resource
    private OwnerAwardDetailMapper ownerAwardDetailMapper;

    @Override
    public boolean insert(OwnerAwardDetail record) {
        return ownerAwardDetailMapper.insert(record) > 0;
    }

    @Override
    public boolean batchInsert(List<OwnerAwardDetail> details) {
        return ownerAwardDetailMapper.batchInsert(details) > 0;
    }
}
