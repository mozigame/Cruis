package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerOperateFlowDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 人工入款汇总
 */
@Component
public interface OwnerOperateFlowDetailMapper {

    /**
     * 添加人工入款汇总
     * @param summmary
     * @return
     */
    int insert(OwnerOperateFlowDetail summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateDetail(OwnerOperateFlowDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerOperateFlowDetail> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerOperateFlowDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}