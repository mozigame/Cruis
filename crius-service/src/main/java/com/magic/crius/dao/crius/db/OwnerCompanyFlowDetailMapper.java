package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerCompanyFlowDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 公司入款明细
 */
@Component
public interface OwnerCompanyFlowDetailMapper {

    /**
     * 添加
     * @param summmary
     * @return
     */
    int insert(OwnerCompanyFlowDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerCompanyFlowDetail> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateDetail(OwnerCompanyFlowDetail summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyFlowDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}