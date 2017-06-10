package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerOnlineFlowDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 线上入款汇总
 */
@Component
public interface OwnerOnlineFlowSummmaryMapper {

    /**
     * 添加
     * @param summmary
     * @return
     */
    int insert(OwnerOnlineFlowDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerOnlineFlowDetail> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerOnlineFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerOnlineFlowDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);

}