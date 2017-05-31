package com.magic.crius.dao.db;

import com.magic.crius.po.OwnerCompanyAccountSummmary;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerCompanyAccountSummmaryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OwnerCompanyAccountSummmary record);

    int insertSelective(OwnerCompanyAccountSummmary record);

    OwnerCompanyAccountSummmary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OwnerCompanyAccountSummmary record);

    int updateByPrimaryKey(OwnerCompanyAccountSummmary record);
}