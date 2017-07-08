package com.magic.crius.service.impl;

import com.magic.crius.po.BillInfo;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.storage.db.BillInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:17
 */
@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Resource
    private BillInfoDbService billInfoDbService;

    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoDbService.save(billInfo);
    }
}
