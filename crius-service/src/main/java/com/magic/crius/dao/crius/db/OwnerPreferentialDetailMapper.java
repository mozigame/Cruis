package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerPreferentialDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 优惠汇总
 */
@Component
public interface OwnerPreferentialDetailMapper {

    /**
     * @param summmary
     * @return
     */
    int insert(OwnerPreferentialDetail summmary);

    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateDetail(OwnerPreferentialDetail summmary);

    /**
     * 批量添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerPreferentialDetail> summmaries);


    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerPreferentialDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);

}