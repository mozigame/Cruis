package com.magic.crius.service;

import com.magic.crius.po.ProxyBillSummary2cost;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:35
 */
public interface ProxyBillSummary2costService {

    boolean save(ProxyBillSummary2cost cost);

    boolean batchInsert(List<ProxyBillSummary2cost> costs);
}
