package com.magic.crius.service.impl;

import com.magic.crius.po.ProxyInfo;
import com.magic.crius.service.ProxyInfoService;
import com.magic.crius.storage.db.ProxyInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:52
 */
@Service
public class ProxyInfoServiceImpl implements ProxyInfoService {

    @Resource
    private ProxyInfoDbService proxyInfoDbService;


    @Override
    public boolean batchInsert(List<ProxyInfo> infos) {
        return proxyInfoDbService.batchInsert(infos);
    }
}
