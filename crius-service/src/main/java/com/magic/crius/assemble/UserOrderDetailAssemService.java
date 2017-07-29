package com.magic.crius.assemble;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.service.UserOrderDetailService;
import com.magic.crius.vo.BaseOrderReq;
import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.JpReq;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
            List<Long> userNoPaidIds = new ArrayList<>();
            List<UserOrderDetail>  userOrderPaid = new ArrayList<>();
            List<UserOrderDetail>  userOrderNoPaid = new ArrayList<>();
            for (UserOrderDetail orderDetail : details) {
                if (orderDetail.getIsPaid() == IsPaidType.noPay.value()) {
                    userNoPaidIds.add(orderDetail.getUserId());
                    userOrderNoPaid.add(orderDetail);
                } else {
                    userOrderPaid.add(orderDetail);
                }
            }
//            for ()
            //批量添加未派彩的数据
            tethysUserOrderDetailService.batchSave(userOrderNoPaid, userNoPaidIds);

            flag = true;
        }
        return flag;
    }
    
    public boolean batchSaveDetails(List<UserOrderDetail> details) {
        boolean flag = false;

		List<Long> userIds = new ArrayList<>();
		for (UserOrderDetail orderDetail : details) {
			userIds.add(orderDetail.getUserId());
		}
		tethysUserOrderDetailService.batchSave(details, userIds);
        return flag;
    }
    
    
    private UserOrderDetail assembleUserOrderDetail(BaseOrderReq req) {
        UserOrderDetail detail = new UserOrderDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBcBetId());
        detail.setOrderCount(req.getBetAmount());
        detail.setEffectOrderCount(req.getValidBetAmount());
        detail.setPayOffCount(req.getPayoff());
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getUpdateDatetime()), "yyyyMMdd")));
        detail.setCreateTime(req.getBetDatetime());
        detail.setUpdateTime(req.getUpdateDatetime());
        detail.setOrderExtent(req.getOrderExtent().toJSONString());
        return detail;
    }


    public UserOrderDetail assembleUserOrderDetail(DealerRewardReq req) {
        // TODO Auto-generated method stub
        UserOrderDetail detail = new UserOrderDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBillId());
        detail.setRemark("荷官打赏：" + req.getRewardAmount());
        detail.setOrderCount(0L);
        detail.setEffectOrderCount(0L);
        detail.setPayOffCount(0L);
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());

        return detail;
    }

	public UserOrderDetail assembleUserOrderDetail(JpReq req) {
        // TODO Auto-generated method stub
        UserOrderDetail detail = new UserOrderDetail();
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBillId());
        detail.setRemark("Bonus彩金：" + req.getJpType() == null ? "" : req.getJpType());
        detail.setOrderCount(0L);
        detail.setEffectOrderCount(0L);
        detail.setPayOffCount(req.getJpAmount());
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());

        return detail;
    }
}
