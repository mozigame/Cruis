package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.OwnerPreferentialSummary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 优惠汇总
 */
@Component
public interface OwnerPreferentialSummaryMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(OwnerPreferentialSummary summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerPreferentialSummary summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerPreferentialSummary> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerPreferentialSummary> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);

}