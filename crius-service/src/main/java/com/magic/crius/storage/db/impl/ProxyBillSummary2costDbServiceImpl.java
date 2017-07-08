package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.ProxyBillSummary2costMapper;
import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.storage.db.ProxyBillSummary2costDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:35
 */
@Service
public class ProxyBillSummary2costDbServiceImpl implements ProxyBillSummary2costDbService {

    @Resource
    private ProxyBillSummary2costMapper proxyBillSummary2costMapper;

    @Override
    public boolean save(ProxyBillSummary2cost cost) {
        return proxyBillSummary2costMapper.insert(cost) > 0;
    }
}
