package com.magic.crius.dao.crius.db;

import org.springframework.stereotype.Repository;

import com.magic.crius.po.RiskEventRecord;

@Repository
public interface RiskEventRecordMapper {

    int insert(RiskEventRecord record);

}