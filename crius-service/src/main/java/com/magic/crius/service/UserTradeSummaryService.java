package com.magic.crius.service;

import com.magic.crius.po.UserTradeSummary;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/8/10
 * Time: 14:21
 */
public interface UserTradeSummaryService {

    boolean batchInsert(List<UserTradeSummary> summaries);

    boolean update(UserTradeSummary summary);

    List<UserTradeSummary> getSummaryTypeList(Collection<UserTradeSummary> summaries);
}
