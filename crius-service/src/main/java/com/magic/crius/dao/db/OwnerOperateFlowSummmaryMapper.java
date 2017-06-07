package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerOperateFlowSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 人工入款汇总
 */
@Component
public interface OwnerOperateFlowSummmaryMapper {

    /**
     * 添加人工入款汇总
     * @param summmary
     * @return
     */
    int insert(OwnerOperateFlowSummmary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOperateFlowSummmary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerOperateFlowSummmary> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerOperateFlowSummmary> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}