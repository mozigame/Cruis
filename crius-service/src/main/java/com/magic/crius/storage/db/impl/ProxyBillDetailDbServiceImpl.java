package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.ProxyBillDetailMapper;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.storage.db.ProxyBillDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:32
 */
@Service
public class ProxyBillDetailDbServiceImpl implements ProxyBillDetailDbService {

    @Resource
    private ProxyBillDetailMapper proxyBillDetailMapper;

    @Override
    public boolean save(ProxyBillDetail detail) {
        return proxyBillDetailMapper.insert(detail) > 0;
    }
}
