package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserPreferentialSummaryAssemService;
import com.magic.crius.po.UserPreferentialSummary;
import com.magic.crius.service.UserPreferentialSummaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public void batchSave(List<UserPreferentialSummary> summaries) {

        //todo 错误处理
        if (summaries.size() > 0) {
            boolean saveResult = userPreferentialSummaryService.batchInsert(summaries);
        }
    }
}
