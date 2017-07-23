package com.magic.crius.storage.db;

import com.magic.crius.po.ProxyInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:50
 */
public interface ProxyInfoDbService {

    boolean batchInsert(List<ProxyInfo> infos);

    List<Long> getOwnerIdList();

    /**
     * 获取已经存在的代理的id列表
     * @param proxyIds
     * @return
     */
    List<Long> getExistIds(List<Long> proxyIds);

    boolean update(ProxyInfo info);
}
