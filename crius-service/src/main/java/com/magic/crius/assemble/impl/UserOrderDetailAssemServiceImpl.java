package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.UserOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:31
 */
@Service
public class UserOrderDetailAssemServiceImpl implements UserOrderDetailAssemService {

    @Resource
    private UserOrderDetailService userOrderDetailService;

    @Override
    public boolean batchSave(List<UserOrderDetail> details) {
        return userOrderDetailService.batchSave(details);
    }
}
