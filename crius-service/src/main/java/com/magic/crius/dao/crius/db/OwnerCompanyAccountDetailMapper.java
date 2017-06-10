package com.magic.crius.dao.crius.db;

import com.magic.crius.po.OwnerCompanyAccountDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * 公司账目汇总
 */
@Component
public interface OwnerCompanyAccountDetailMapper {

    /**
     * 添加
     * @param detail
     * @return
     */
    int insert(OwnerCompanyAccountDetail detail);

    /**
     * 添加
     * @param details
     * @return
     */
    int batchInsert(@Param("list") Collection<OwnerCompanyAccountDetail> details);


    /**
     * 修改
     * @param detail
     * @return
     */
    int updateDetail(OwnerCompanyAccountDetail detail);

    /**
     * 查询当天内多个业主下的数据
     * @return
     */
    List<OwnerCompanyAccountDetail> findByOwnerIds(@Param("list") Collection<Long> ownerIds, @Param("pdate") Integer pdate);

}