package com.magic.crius.storage.db;

import java.util.List;

import com.magic.crius.po.RiskEventRecord;

/**
 * User: justin
 * Date: 2017/07/18
 */
public interface RiskEventRecordDbService {

    boolean insert(RiskEventRecord record);
}
