package com.magic.crius.service;

import java.util.List;

import com.magic.crius.po.RiskEventDetail;

/**
 * User: justin
 * Date: 2017/07/18
 */
public interface RiskEventDetailService {

    boolean insert(RiskEventDetail record);

    boolean batchInsert(List<RiskEventDetail> infos);
}
