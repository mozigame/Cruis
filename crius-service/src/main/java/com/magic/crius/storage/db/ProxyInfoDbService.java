package com.magic.crius.storage.db;

import java.util.List;

import com.magic.crius.po.ProxyInfo;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:50
 */
public interface ProxyInfoDbService {

    boolean batchInsert(List<ProxyInfo> infos);

    List<Long> getOwnerIdList();
    
    List<ProxyInfo> getProxyInfoList(List<Long> proxyIdList); 

    /**
     * 获取已经存在的代理的id列表
     * @param proxyIds
     * @return
     */
    List<Long> getExistIds(List<Long> proxyIds);

    boolean update(ProxyInfo info);
}
