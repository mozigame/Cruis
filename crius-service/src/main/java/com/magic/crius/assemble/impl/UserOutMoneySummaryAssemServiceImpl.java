package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserOutMoneySummaryAssemService;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.UserOutMoneySummaryService;
import org.springframework.stereotype.Component;
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
public class UserOutMoneySummaryAssemServiceImpl implements UserOutMoneySummaryAssemService {

    @Resource
    private UserOutMoneySummaryService userOutMoneySummaryService;

    @Override
    public void batchSave(Map<Long, UserOutMoneySummary> summaries) {
        Set<Long> userIds = summaries.keySet();
        int pdate = summaries.values().iterator().next().getPdate();
        List<UserOutMoneySummary> flowSummmaries = userOutMoneySummaryService.findByUserIds(userIds, pdate);
        Collection<UserOutMoneySummary> saves = new ArrayList<>();
        List<UserOutMoneySummary> updates = new ArrayList<>();

        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            for (Long key : userIds) {
                boolean flag = false;
                for (UserOutMoneySummary summmary : flowSummmaries) {
                    if (key.longValue() == summmary.getUserId().longValue()) {
                        flag = true;
                        flowSummmaries.remove(summmary);
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
            boolean saveResult = userOutMoneySummaryService.batchInsert(saves);
        }
        for (UserOutMoneySummary summmary : updates) {
            userOutMoneySummaryService.updateSummary(summmary);
        }
    }
}
