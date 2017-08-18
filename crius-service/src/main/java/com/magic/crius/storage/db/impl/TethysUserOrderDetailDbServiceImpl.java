package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.UserOrderDetailMapper;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.storage.db.TethysUserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:38
 */
@Service
public class TethysUserOrderDetailDbServiceImpl implements TethysUserOrderDetailDbService {

    @Resource(name = "tethysUserOrderDetailMapper")
    private UserOrderDetailMapper userOrderDetailMapper;

    @Override
    public boolean batchSave(List<UserOrderDetail> userOrderDetails, List<Long> userIds) {
        try {
            return userOrderDetailMapper.insertBatch(userOrderDetails, userIds) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePaid(UserOrderDetail orderDetail) {
        try {
            return userOrderDetailMapper.update("updatePaidStatus", orderDetail.getUserId(), new String[]{"param"}, new Object[]{orderDetail}) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserOrderDetail> findNoPaidIds(Collection<UserOrderDetail> orderDetails) {
        List<UserOrderDetail> result = new ArrayList<>();
        for (UserOrderDetail orderDetail : orderDetails) {
            try {
                UserOrderDetail userOrderDetail = (UserOrderDetail) userOrderDetailMapper.get("findNoPaid", orderDetail.getUserId(),
                        new String[]{"userId", "orderId"}, new Object[]{orderDetail.getUserId(), orderDetail.getOrderId()});
                if (userOrderDetail != null) {
                    result.add(userOrderDetail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
