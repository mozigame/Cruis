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
    public void batchSave(List<UserOutMoneySummary> summaries) {

        //todo 错误处理
        if (summaries.size() > 0) {
            boolean saveResult = userOutMoneySummaryService.batchInsert(summaries);
        }
    }
}
