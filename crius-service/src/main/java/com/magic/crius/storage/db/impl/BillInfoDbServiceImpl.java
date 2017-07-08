package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.BillInfoMapper;
import com.magic.crius.po.BillInfo;
import com.magic.crius.storage.db.BillInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:15
 *账单汇总表
 */
@Service
public class BillInfoDbServiceImpl implements BillInfoDbService {

    @Resource
    private BillInfoMapper billInfoMapper;

    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoMapper.insert(billInfo) > 0;
    }
}
