package com.magic.crius.service.impl;

import com.magic.api.commons.mq.Producer;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.service.ProxyBillDetailService;
import com.magic.crius.storage.db.ProxyBillDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:33
 */
@Service
public class ProxyBillDetailServiceImpl implements ProxyBillDetailService {

    @Resource
    private ProxyBillDetailDbService proxyBillDetailDbService;

    @Override
    public boolean save(ProxyBillDetail detail) {
        return proxyBillDetailDbService.save(detail);
    }
}
