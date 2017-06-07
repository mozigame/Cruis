package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.service.UserAccountSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:21
 */
@Service
public class UserAccountSummaryAssemServiceImpl implements UserAccountSummaryAssemService {

    @Resource
    private UserAccountSummaryService userAccountSummaryService;


    @Override
    public boolean updateRecharge(Map<Long, UserAccountSummary> summaries) {
        batchSave(summaries);
        return true;
    }

    @Override
    public boolean updateWithdraw(Map<Long, UserAccountSummary> summaries) {
        batchSave(summaries);
        return true;
    }

    private void batchSave(Map<Long, UserAccountSummary> summaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (UserAccountSummary summmary : summaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = summaries.values().iterator().next().getPdate();
        List<UserAccountSummary> flowSummmaries = userAccountSummaryService.findByOwnerIds(ownerIds, pdate);
        List<UserAccountSummary> saves = new ArrayList<>();
        List<UserAccountSummary> updates = new ArrayList<>();

        Set<Long> keys = summaries.keySet();
        for (UserAccountSummary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getUserId())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = userAccountSummaryService.batchInsert(saves);
        for (UserAccountSummary summmary : updates) {
            userAccountSummaryService.updateSummary(summmary);
        }
    }
}
