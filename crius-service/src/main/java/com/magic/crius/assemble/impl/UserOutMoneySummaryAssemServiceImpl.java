package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserOutMoneySummaryAssemService;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.UserOutMoneySummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:43
 * 会员出款汇总
 */
@Service
public class UserOutMoneySummaryAssemServiceImpl implements UserOutMoneySummaryAssemService{

    @Resource
    private UserOutMoneySummaryService userOutMoneySummaryService;

    @Override
    public void batchSave(Map<Long, UserOutMoneySummary> summaries) {
        Set<Long> ownerIds = new HashSet<>();
        for (UserOutMoneySummary summmary : summaries.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = summaries.values().iterator().next().getPdate();
        List<UserOutMoneySummary> flowSummmaries = userOutMoneySummaryService.findByOwnerIds(ownerIds, pdate);
        List<UserOutMoneySummary> saves = new ArrayList<>();
        List<UserOutMoneySummary> updates = new ArrayList<>();

        Set<Long> keys = summaries.keySet();
        for (UserOutMoneySummary summmary : flowSummmaries) {
            if (!keys.contains(summmary.getUserId())) {
                saves.add(summmary);
            } else {
                updates.add(summmary);
            }
        }
        //todo 错误处理
        boolean saveResult = userOutMoneySummaryService.batchInsert(saves);
        for (UserOutMoneySummary summmary : updates) {
            userOutMoneySummaryService.updateSummary(summmary);
        }
    }
}
