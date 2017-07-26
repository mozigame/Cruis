package com.magic.crius.dao.crius.db;

import com.magic.crius.po.ProxyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 代理基础信息
 */
@Component
public interface ProxyInfoMapper {

    int insert(ProxyInfo record);

    int batchInsert(@Param("list") List<ProxyInfo> infos);

    List<Long> getOwnerIdList();
    
    List<ProxyInfo> getProxyInfoList(@Param("proxyIdList")List<Long> proxyIdList ); 

    /**
     * 获取已经存在的代理的id列表
     * @param proxyIds
     * @return
     */
    List<Long> getExistIds(List<Long> proxyIds);

    int update(ProxyInfo info);

}