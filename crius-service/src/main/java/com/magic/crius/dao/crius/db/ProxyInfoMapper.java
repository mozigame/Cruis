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

}