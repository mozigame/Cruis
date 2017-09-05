package com.magic.crius.storage.db.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
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
            ApiLogger.error("tethys user order batchSave error ,param : " + JSON.toJSONString(userOrderDetails), e);
        }
        return false;
    }

    @Override
    public boolean save(UserOrderDetail detail) {
        try {
            return userOrderDetailMapper.insert(detail.getUserId(), detail) != null;
        } catch (Exception e) {
            ApiLogger.error("tethys user order save error ,param : " + JSON.toJSONString(detail), e);
        }
        return false;
    }

    @Override
    public boolean updatePaid(UserOrderDetail orderDetail) {
        try {
            return userOrderDetailMapper.update("updatePaidStatus", orderDetail.getUserId(), new String[]{"param"}, new Object[]{orderDetail}) > 0;
        } catch (Exception e) {
            ApiLogger.error("tethys user order updatePaid error ,param : " + JSON.toJSONString(orderDetail), e);
        }
        return false;
    }

    @Override
    public List<UserOrderDetail> findByOrderId(UserOrderDetail detail) {
        try {
            return userOrderDetailMapper.findCustom("findByOrderId", detail.getUserId(),
                    new String[]{"userId", "orderId"}, new Object[]{detail.getUserId(), detail.getOrderId()});
        } catch (Exception e) {
            ApiLogger.error("tethys user order findByOrderId error ,param : " + JSON.toJSONString(detail), e);
        }
        return null;
    }
}
