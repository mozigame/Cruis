package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 公司账目汇总
 */
@Component
public interface OwnerCompanyAccountSummmaryMapper {

    /**
     * 添加
     * @param summmary
     * @return
     */
    int insert(OwnerCompanyAccountSummmary summmary);

    /**
     * 添加
     * @param summmaries
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerCompanyAccountSummmary> summmaries);


    /**
     * 修改
     * @param summmary
     * @return
     */
    int updateSummary(OwnerCompanyAccountSummmary summmary);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountSummmary> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);

}