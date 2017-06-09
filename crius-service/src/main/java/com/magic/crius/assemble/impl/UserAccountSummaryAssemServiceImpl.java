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
    public boolean updateRecharge(List<UserAccountSummary> summaries) {
        batchSave(summaries);
        return true;
    }

    @Override
    public boolean updateWithdraw(List<UserAccountSummary> summaries) {
        batchSave(summaries);
        return true;
    }

    private void batchSave(List<UserAccountSummary> summaries) {
        //todo 错误处理,此处只是插入数据，如果插入失败需要增加容错机制处理
        if (summaries.size() > 0) {
            boolean saveResult = userAccountSummaryService.batchInsert(summaries);

        }
    }
}
