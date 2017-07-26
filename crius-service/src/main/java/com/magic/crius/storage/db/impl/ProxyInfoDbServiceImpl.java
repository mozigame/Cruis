package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.ProxyInfoMapper;
import com.magic.crius.po.ProxyInfo;
import com.magic.crius.storage.db.ProxyInfoDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:51
 */
@Service
public class ProxyInfoDbServiceImpl implements ProxyInfoDbService {

    @Resource
    private ProxyInfoMapper proxyInfoMapper;

    @Override
    public boolean batchInsert(List<ProxyInfo> infos) {
        return proxyInfoMapper.batchInsert(infos) > 0;
    }
    
    public List<ProxyInfo> getProxyInfoList(List<Long> proxyIdList){
    	return proxyInfoMapper.getProxyInfoList(proxyIdList);
    }

    @Override
    public List<Long> getOwnerIdList() {
        return proxyInfoMapper.getOwnerIdList();
    }

    @Override
    public List<Long> getExistIds(List<Long> proxyIds) {
        return proxyInfoMapper.getExistIds(proxyIds);
    }

    @Override
    public boolean update(ProxyInfo info) {
        return proxyInfoMapper.update(info) > 0;
    }
}
