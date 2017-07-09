package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerBillSummary2cost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:20
 */
public interface OwnerBillSummary2costDbService {

    boolean save(OwnerBillSummary2cost cost);

    boolean batchInsert(List<OwnerBillSummary2cost> costs);
}
