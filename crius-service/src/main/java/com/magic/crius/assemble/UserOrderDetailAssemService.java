package com.magic.crius.assemble;

import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.service.UserOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:31
 */
@Service
public class UserOrderDetailAssemService {

    @Resource
    private UserOrderDetailService userOrderDetailService;
    @Resource
    private TethysUserOrderDetailService tethysUserOrderDetailService;

    public boolean batchSave(List<UserOrderDetail> details) {
        boolean flag = false;
        if (userOrderDetailService.batchSave(details)) {
            List<Long> userIds = new ArrayList<>();
            for (UserOrderDetail orderDetail : details) {
                userIds.add(orderDetail.getUserId());
            }
            tethysUserOrderDetailService.batchSave(details, userIds);
            flag = true;
        }
        return flag;
    }
}
