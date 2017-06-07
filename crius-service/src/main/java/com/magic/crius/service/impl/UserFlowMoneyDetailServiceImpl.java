package com.magic.crius.service.impl;

import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.service.UserFlowMoneyDetailService;
import com.magic.crius.storage.db.UserFlowMoneyDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:05
 */
@Service
public class UserFlowMoneyDetailServiceImpl implements UserFlowMoneyDetailService {

    @Resource
    private UserFlowMoneyDetailDbService userFlowMoneyDetailDbService;

    @Override
    public boolean save(UserFlowMoneyDetail detail) {
        return userFlowMoneyDetailDbService.save(detail);
    }

    @Override
    public boolean batchSave(List<UserFlowMoneyDetail> details) {
        return userFlowMoneyDetailDbService.batchSave(details);
    }
}
