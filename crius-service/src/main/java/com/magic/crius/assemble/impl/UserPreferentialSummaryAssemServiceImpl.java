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
        Set<Long> ownerIds = new HashSet<>();
        for (UserPreferentialSummary summmary : summaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = summaries.values().iterator().next().getPdate();
        List<UserPreferentialSummary> flowSummmaries = userPreferentialSummaryService.findByOwnerIds(ownerIds, pdate);
        List<UserPreferentialSummary> saves = new ArrayList<>();
        List<UserPreferentialSummary> updates = new ArrayList<>();

        Set<String> keys = summaries.keySet();
        for (UserPreferentialSummary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getUserId() + "_" +summmary.getPreferentialType())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = userPreferentialSummaryService.batchInsert(saves);
        for (UserPreferentialSummary summmary : updates) {
            userPreferentialSummaryService.updateSummary(summmary);
        }
    }
}
