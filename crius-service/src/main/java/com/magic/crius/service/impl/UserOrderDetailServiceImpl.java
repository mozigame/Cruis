package com.magic.crius.service.impl;

import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.UserOrderDetailService;
import com.magic.crius.storage.db.UserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:30
 */
@Service
public class UserOrderDetailServiceImpl implements UserOrderDetailService {


    @Resource
    private UserOrderDetailDbService userOrderDetailDbService;


    @Override
    public boolean batchSave(List<UserOrderDetail> details) {
        return userOrderDetailDbService.batchSave(details);
    }
}
