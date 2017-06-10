package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserPreferentialDetailAssemService;
import com.magic.crius.po.UserPreferentialDetail;
import com.magic.crius.service.UserPreferentialDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:37
 */
@Service
public class UserPreferentialDetailAssemServiceImpl implements UserPreferentialDetailAssemService {

    @Resource
    private UserPreferentialDetailService userPreferentialDetailService;

    @Override
    public void batchSave(List<UserPreferentialDetail> summaries) {

        //todo 错误处理
        if (summaries.size() > 0) {
            boolean saveResult = userPreferentialDetailService.batchInsert(summaries);
        }
    }
}
