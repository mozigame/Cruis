package com.magic.crius.service.impl;

import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.storage.db.TethysUserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
    public boolean batchSave(List<UserOrderDetail> userOrderDetails, List<Long> userIds) {
        return tethysUserOrderDetailDbService.batchSave(userOrderDetails,userIds);
    }

    @Override
    public boolean updatePaid(UserOrderDetail orderDetail) {
        return tethysUserOrderDetailDbService.updatePaid(orderDetail);
    }

    @Override
    public List<UserOrderDetail> findNoPaidIds(Collection<UserOrderDetail> orderDetails) {
        return tethysUserOrderDetailDbService.findNoPaidIds(orderDetails);
    }
}
