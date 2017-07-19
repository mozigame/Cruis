package com.magic.crius.storage.db;

import com.magic.crius.po.RiskEventDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:54
 */
public interface RiskEventDetailDbService {

    boolean insert(RiskEventDetail record);

    boolean batchInsert(List<RiskEventDetail> infos);
}
