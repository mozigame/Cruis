package com.magic.crius.assemble;

import com.magic.crius.po.ProxyPreferentialSummary;
import com.magic.crius.po.UserPreferentialSummary;

import java.util.Collection;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:37
 * 会员优惠汇总
 */
public interface UserPreferentialSummaryAssemService {

    /**
     * @param summaries
     * @return
     */
    void batchSave(Map<String, UserPreferentialSummary> summaries);
}
