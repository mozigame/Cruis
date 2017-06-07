package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.db.UserFlowMoneyDetailMapper;
import com.magic.crius.po.UserFlowMoneyDetail;
import com.magic.crius.storage.db.UserFlowMoneyDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 19:04
 */
@Service
public class UserFlowMoneyDetailDbServiceImpl implements UserFlowMoneyDetailDbService {

    @Resource
    private UserFlowMoneyDetailMapper userFlowMoneyDetailMapper;

    @Override
    public boolean save(UserFlowMoneyDetail detail) {
        return userFlowMoneyDetailMapper.insert(detail) > 0;
    }

    @Override
    public boolean batchSave(List<UserFlowMoneyDetail> details) {
        return userFlowMoneyDetailMapper.batchInsert(details) > 0;
    }
}
