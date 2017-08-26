package com.magic.crius.assemble;

import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.service.UserOrderDetailService;
import com.magic.crius.vo.BaseOrderReq;
import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.JpReq;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:31
 */
@Service
public class UserOrderDetailAssemService {

    private Logger logger = Logger.getLogger(UserOrderDetailAssemService.class);
    @Resource
    private UserOrderDetailService userOrderDetailService;
    @Resource
    private TethysUserOrderDetailService tethysUserOrderDetailService;
    @Resource
    private GameInfoService gameInfoService;

    public boolean batchSave(List<UserOrderDetail> details) {
        List<Long> insertUserIds = new ArrayList<>();
        Map<Long, UserOrderDetail> userOrderPaid = new HashMap<>();
        //插入的订单
        List<UserOrderDetail> insertOrder = new ArrayList<>();
        //修改的订单
        List<UserOrderDetail> updateOrder = new ArrayList<>();
        for (UserOrderDetail orderDetail : details) {
            if (orderDetail.getIsPaid() != null && orderDetail.getIsPaid() == IsPaidType.noPaid.value()) {
                insertUserIds.add(orderDetail.getUserId());
                insertOrder.add(orderDetail);
            } else {
                userOrderPaid.put(orderDetail.getOrderId(), orderDetail);
            }
        }
        //数据库中已经存在的订单
        List<UserOrderDetail> existNoPaidIds = tethysUserOrderDetailService.findNoPaidIds(userOrderPaid.values());
        for (Map.Entry<Long, UserOrderDetail> entry : userOrderPaid.entrySet()) {
            UserOrderDetail detail = null;
            for (UserOrderDetail _detail : existNoPaidIds) {
                if (_detail.getOrderId().equals(entry.getKey())) {
                    if (_detail.getIsPaid() == IsPaidType.noPaid.value()) {
                        updateOrder.add(entry.getValue());
                        detail = _detail;
                        break;
                    } else {
                        detail = _detail;
                        break;
                    }
                }
            }
            if (detail == null) {
                insertUserIds.add(entry.getValue().getUserId());
                insertOrder.add(entry.getValue());
            }else{
                existNoPaidIds.remove(detail);
            }

        }
        /*
         * 批量添加未派彩和派彩但未插入的数据
         */
        //tethys
        if (insertOrder.size() > 0) {
            tethysUserOrderDetailService.batchSave(insertOrder, insertUserIds);
            //metis
            userOrderDetailService.batchSave(insertOrder);
        }
        //修改订单的派彩状态
        if (updateOrder.size() > 0) {
            for (UserOrderDetail orderDetail : updateOrder) {
                tethysUserOrderDetailService.updatePaid(orderDetail);
                userOrderDetailService.updatePaidStatus(orderDetail);
            }
        }
        logger.info(String.format(" all order size : %d ,insert order size : %d, update order size : %d", details.size(), insertOrder.size(), updateOrder.size()));

        return true;
    }

    public boolean batchSaveDetails(List<UserOrderDetail> details) {
        logger.info("batch save userOrderDetail size : " + details.size());
        boolean flag = false;
        List<Long> userIds = new ArrayList<>();
        for (UserOrderDetail orderDetail : details) {
            userIds.add(orderDetail.getUserId());
        }
        tethysUserOrderDetailService.batchSave(details, userIds);
        flag = true;

        return flag;
    }

    public UserOrderDetail assembleUserOrderDetail(DealerRewardReq req) {
        // TODO Auto-generated method stub
        UserOrderDetail detail = new UserOrderDetail();
        detail.setId(req.getReqId());
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        //打赏的gameId是gameType，需要在数据库中拿到一个随机的gameId
        detail.setGameId(gameInfoService.getGameId(req.getGameId() + ""));
        detail.setOrderId(req.getBillId());
        detail.setRemark("荷官打赏：" + req.getRewardAmount());
        detail.setOrderCount(0L);
        detail.setEffectOrderCount(0L);
        detail.setPayOffCount(0L);
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());
        detail.setIsPaid(IsPaidType.paid.value());
        return detail;
    }

    public UserOrderDetail assembleUserOrderDetail(JpReq req) {
        // TODO Auto-generated method stub
        UserOrderDetail detail = new UserOrderDetail();
        detail.setId(req.getReqId());
        detail.setOwnerId(req.getOwnerId());
        detail.setUserId(req.getUserId());
        detail.setGameId(String.valueOf(req.getGameId()));
        detail.setOrderId(req.getBillId());
        detail.setRemark("Bonus彩金：" + (req.getJpType() == null ? "" : req.getJpType()));
        detail.setOrderCount(0L);
        detail.setEffectOrderCount(0L);
        detail.setPayOffCount(req.getJpAmount());
        //todo 订单状态
        detail.setOrderState(0);
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());
        detail.setIsPaid(IsPaidType.paid.value());
        return detail;
    }

    public UserOrderDetail assembleUserOrderDetail(BaseOrderReq req) {
        UserOrderDetail detail = new UserOrderDetail();
        detail.setId(req.getReqId());
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
        if (req.getIsPaid() != null) {
            detail.setIsPaid(req.getIsPaid());
        }
        detail.setOrderExtent(req.getOrderExtent().toJSONString());
        return detail;
    }
}
