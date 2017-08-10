package com.magic.crius.service.impl;

import com.magic.crius.po.UserTradeSummary;
import com.magic.crius.service.UserTradeSummaryService;
import com.magic.crius.storage.db.UserTradeSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/8/10
 * Time: 14:28
 */
@Service
public class UserTradeSummaryServiceImpl implements UserTradeSummaryService {

    @Resource
    private UserTradeSummaryDbService userTradeSummaryDbService;

    @Override
    public boolean batchInsert(List<UserTradeSummary> summaries) {
        return userTradeSummaryDbService.batchInsert(summaries);
    }

    @Override
    public boolean update(UserTradeSummary summary) {
        return userTradeSummaryDbService.update(summary);
    }

    @Override
    public List<UserTradeSummary> getSummaryTypeList(Collection<UserTradeSummary> summaries) {
        return userTradeSummaryDbService.getSummaryTypeList(summaries);
    }
}
