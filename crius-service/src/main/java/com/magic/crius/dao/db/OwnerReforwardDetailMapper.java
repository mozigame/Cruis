package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerReforwardDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 返水详情
 */
@Component
public interface OwnerReforwardDetailMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(OwnerReforwardDetail summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerReforwardDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerReforwardDetail> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerReforwardDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);
}