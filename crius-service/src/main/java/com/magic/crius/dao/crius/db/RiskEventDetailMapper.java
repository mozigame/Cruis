package com.magic.crius.dao.crius.db;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.magic.crius.po.RiskEventDetail;

@Repository
public interface RiskEventDetailMapper {

    int insert(RiskEventDetail record);
    
    int batchInsert(List<RiskEventDetail> record);
}