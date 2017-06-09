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
    public boolean updateWithdraw(List<UserAccountSummary> summaries) {
        batchSave(summaries);
        return true;
    }

    private void batchSave(Map<Long, UserAccountSummary> summaries) {
        Set<Long> userIds = summaries.keySet();

        int pdate = summaries.values().iterator().next().getPdate();
        List<UserAccountSummary> flowSummmaries = userAccountSummaryService.findByUserIds(userIds, pdate);
        Collection<UserAccountSummary> saves = new ArrayList<>();
        List<UserAccountSummary> updates = new ArrayList<>();

        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            for (Long key : userIds) {
                boolean flag = false;
                for (UserAccountSummary summmary : flowSummmaries) {
                    if (key.longValue() == summmary.getUserId().longValue()) {
                        flowSummmaries.remove(summmary);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    updates.add(summaries.get(key));
                } else {
                    saves.add(summaries.get(key));
                }
            }
        } else {
            saves = summaries.values();
        }


        //todo 错误处理
        if (saves.size() > 0) {
            boolean saveResult = userAccountSummaryService.batchInsert(saves);
        }
        for (UserAccountSummary summmary : updates) {
            userAccountSummaryService.updateSummary(summmary);
        }
    }
}
