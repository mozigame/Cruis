package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserOutMoneySummaryAssemService;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.UserOutMoneySummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 0:43
 * 会员出款
 */
@Service
public class UserOutMoneySummaryAssemServiceImpl implements UserOutMoneySummaryAssemService{

    @Resource
    private UserOutMoneySummaryService userOutMoneySummaryService;

    @Override
    public void batchSave(Collection<UserOutMoneySummary> userOutMoneySummaries) {
        for (UserOutMoneySummary summary: userOutMoneySummaries) {
            if (userOutMoneySummaryService.checkExist(summary.getOwnerId(), summary.getUserId(), summary.getPdate())) {
                if (!userOutMoneySummaryService.updateSummary(summary)) {
                    //TODO
                }
            } else {
                if (!userOutMoneySummaryService.save(summary)) {
                    //TODO
                }
            }
        }
    }
}
