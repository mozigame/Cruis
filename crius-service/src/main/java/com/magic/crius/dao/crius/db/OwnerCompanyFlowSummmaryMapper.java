package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerCompanyFlowSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 公司入款明细
 */
@Component
public interface OwnerCompanyFlowSummmaryMapper {

    /**
     * 添加
     * @param summmary
     * @return
     */
    int insert(OwnerCompanyFlowSummmary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerCompanyFlowSummmary> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerCompanyFlowSummmary summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowSummmary> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}