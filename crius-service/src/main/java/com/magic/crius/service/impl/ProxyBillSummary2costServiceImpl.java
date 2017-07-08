package com.magic.crius.service.impl;

import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.service.ProxyBillSummary2costService;
import com.magic.crius.storage.db.ProxyBillSummary2costDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:36
 */
@Service
public class ProxyBillSummary2costServiceImpl implements ProxyBillSummary2costService {

    @Resource
    private ProxyBillSummary2costDbService proxyBillSummary2costDbService;


    @Override
    public boolean save(ProxyBillSummary2cost cost) {
        return proxyBillSummary2costDbService.save(cost);
    }
}
