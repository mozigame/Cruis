package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OwnerCompanyAccountSummmaryMapper {

    /**
     * 添加
     * @param flowSummmary
     * @return
     */
    int insert(OwnerCompanyAccountSummmary flowSummmary);

    /**
     * 修改
     * @param flowSummmary
     * @return
     */
    boolean updateSummary(OwnerCompanyAccountSummmary flowSummmary);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param summaryType
     * @param pdate
     * @return
     */
    int checkExist(@Param("ownerId") Long ownerId, @Param("summaryType") Integer summaryType, @Param("pdate") Integer pdate);


}