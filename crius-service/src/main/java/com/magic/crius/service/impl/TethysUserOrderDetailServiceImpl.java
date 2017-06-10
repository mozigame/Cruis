package com.magic.crius.service.impl;

import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.storage.db.TethysUserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:41
 */
@Service
public class TethysUserOrderDetailServiceImpl implements TethysUserOrderDetailService {

    @Resource
    private TethysUserOrderDetailDbService tethysUserOrderDetailDbService;


    @Override
    public boolean batchSave(Collection<UserOrderDetail> userOrderDetails) {
        return tethysUserOrderDetailDbService.batchSave(userOrderDetails);
    }
}
