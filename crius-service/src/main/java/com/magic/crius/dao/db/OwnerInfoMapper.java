package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 业主基础信息
 */
@Component
public interface OwnerInfoMapper {

    int insert(OwnerInfo record);

    int batchInsert(@Param("list") List<OwnerInfo> infos);

    OwnerInfo get(@Param("ownerId") Long ownerId);
}