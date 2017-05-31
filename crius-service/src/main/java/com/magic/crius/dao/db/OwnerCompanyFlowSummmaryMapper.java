package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerCompanyFlowSummmary;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerCompanyFlowSummmaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OwnerCompanyFlowSummmary record);

    int insertSelective(OwnerCompanyFlowSummmary record);

    OwnerCompanyFlowSummmary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OwnerCompanyFlowSummmary record);

    int updateByPrimaryKey(OwnerCompanyFlowSummmary record);
}