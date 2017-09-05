package com.magic.crius.assemble;

import com.magic.analysis.enums.GameTypeEnum;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.enums.ProcessStatus;
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
        //插入的订单
        List<UserOrderDetail> insertOrder = new ArrayList<>();
        //需要做修改的订单
        List<UserOrderDetail> updateOrder = new ArrayList<>();

        details.forEach((orderDetail)->{
            if (orderDetail.getGameAbstractType() == null) {
                orderDetail.setGameAbstractType(Integer.parseInt(GameTypeEnum.OTHER.getCode()));
            }
            GameTypeEnum gameTypeEnum = GameTypeEnum.getEnumByCode(orderDetail.getGameAbstractType());
            if (gameTypeEnum == GameTypeEnum.LOTTERY || gameTypeEnum == GameTypeEnum.PHYSICAL) {
                if (orderDetail.getIsPaid() != null && orderDetail.getIsPaid() == IsPaidType.noPaid.value()) {
                    insertUserIds.add(orderDetail.getUserId());
                    insertOrder.add(orderDetail);
                } else {
                    updateOrder.add(orderDetail);
                }
            } else {
                insertUserIds.add(orderDetail.getUserId());
                insertOrder.add(orderDetail);
            }
        });

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
            updateOrder.forEach((orderDetail) -> {
                if (!tethysUserOrderDetailService.updatePaid(orderDetail) && !userOrderDetailService.updatePaid(orderDetail)) {
                    orderDetail.setProcStatus(ProcessStatus.failed.status());
                    orderDetail.setConsumerTime(System.currentTimeMillis());
                    userOrderDetailService.saveUpdateFailed(orderDetail);
                }
            });
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
        detail.setOrderState(ProcessStatus.init.status());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());
        detail.setIsPaid(IsPaidType.paid.value());

        detail.setGameAbstractType(req.getGameAbstractType());
        detail.setProcStatus(ProcessStatus.init.status());
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
        detail.setOrderState(ProcessStatus.init.status());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getCreateTime()), "yyyyMMdd")));
        detail.setCreateTime(req.getCreateTime());
        detail.setIsPaid(IsPaidType.paid.value());

        detail.setGameAbstractType(req.getGameAbstractType());
        detail.setProcStatus(ProcessStatus.init.status());
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
        detail.setOrderState(ProcessStatus.init.status());
        detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getUpdateDatetime()), "yyyyMMdd")));
        detail.setCreateTime(req.getBetDatetime());
        detail.setUpdateTime(req.getUpdateDatetime());
        if (req.getIsPaid() != null) {
            detail.setIsPaid(req.getIsPaid());
        } else {
            detail.setIsPaid(IsPaidType.paid.value());
        }
        detail.setOrderExtent(req.getOrderExtent().toJSONString());

        detail.setGameAbstractType(req.getGameAbstractType());
        detail.setProcStatus(ProcessStatus.init.status());
        return detail;
    }
}
