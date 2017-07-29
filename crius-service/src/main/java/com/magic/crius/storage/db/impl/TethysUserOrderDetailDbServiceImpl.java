package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.UserOrderDetailMapper;
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
    public List<Long> findNoPaidIds(List<UserOrderDetail> orderDetails, List<Long> userIds) {
        List<Long> result = new ArrayList<>();
        for (int i=0;i<userIds.size();i++) {
            try {
                Long orderId = (Long) userOrderDetailMapper.get("findNoPaid", userIds.get(i), new String[]{""}, new Object[]{});
                if (orderId != null) {
                    result.add(orderId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
