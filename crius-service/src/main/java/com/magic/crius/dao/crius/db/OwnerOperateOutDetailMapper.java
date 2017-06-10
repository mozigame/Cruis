package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerOperateOutDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * 人工出款详情
 */
public interface OwnerOperateOutDetailMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(OwnerOperateOutDetail summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOperateOutDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerOperateOutDetail> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerOperateOutDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}