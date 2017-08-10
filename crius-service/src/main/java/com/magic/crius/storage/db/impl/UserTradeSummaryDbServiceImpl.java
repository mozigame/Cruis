package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.UserTradeSummaryMapper;
import com.magic.crius.po.UserTradeSummary;
import com.magic.crius.storage.db.UserTradeSummaryDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * User: joey
 * Date: 2017/8/10
 * Time: 14:22
 */
@Service
public class UserTradeSummaryDbServiceImpl implements UserTradeSummaryDbService {

    @Resource
    private UserTradeSummaryMapper userTradeSummaryMapper;

    @Override
    public boolean batchInsert(List<UserTradeSummary> summaries) {
        return userTradeSummaryMapper.batchInsert(summaries) > 0;
    }

    @Override
    public boolean update(UserTradeSummary summary) {
        return userTradeSummaryMapper.update(summary) > 0;
    }

    @Override
    public List<UserTradeSummary> getSummaryTypeList(Collection<UserTradeSummary> summaries) {
        UserTradeSummary summary = summaries.iterator().next();
        return userTradeSummaryMapper.getSummaryTypeList(summaries, summary.getOwnerId(), summary.getSummaryType());
    }
}
