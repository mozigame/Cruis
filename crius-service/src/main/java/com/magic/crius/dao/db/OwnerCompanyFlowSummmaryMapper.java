package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerCompanyFlowSummmary;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OwnerCompanyFlowSummmaryMapper {

    /**
     * 添加
     * @param flowSummmary
     * @return
     */
    int save(OwnerCompanyFlowSummmary flowSummmary);

    /**
     * 修改
     * @param flowSummmary
     * @return
     */
    boolean updateSummary(OwnerCompanyFlowSummmary flowSummmary);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param accountNum
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("accountNum") Long accountNum, @Param("pdate") Integer pdate);
}