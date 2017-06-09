package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserPreferentialSummaryAssemService;
import com.magic.crius.po.ProxyPreferentialSummary;
import com.magic.crius.po.UserPreferentialSummary;
import com.magic.crius.service.UserPreferentialSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:37
 */
@Service
public class UserPreferentialSummaryAssemServiceImpl implements UserPreferentialSummaryAssemService {

    @Resource
    private UserPreferentialSummaryService userPreferentialSummaryService;

    @Override
    public void batchSave(Map<String, UserPreferentialSummary> summaries) {
        Set<Long> userIds = new HashSet<>();
        for (UserPreferentialSummary summmary : summaries.values()) {
            userIds.add(summmary.getUserId());
        }
        int pdate = summaries.values().iterator().next().getPdate();
        List<UserPreferentialSummary> flowSummmaries = userPreferentialSummaryService.findByUserIds(userIds, pdate);
        Collection<UserPreferentialSummary> saves = new ArrayList<>();
        List<UserPreferentialSummary> updates = new ArrayList<>();

        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = summaries.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (UserPreferentialSummary detail : flowSummmaries) {
                    if (key.equals(detail.getUserId() + "_" + detail.getPreferentialType())) {
                        flowSummmaries.remove(detail);
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
            boolean saveResult = userPreferentialSummaryService.batchInsert(saves);
        }
        for (UserPreferentialSummary summmary : updates) {
            userPreferentialSummaryService.updateSummary(summmary);
        }
    }
}
