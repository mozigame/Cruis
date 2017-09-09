package com.magic.crius.assemble;

import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.vo.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * User: joey
 * Date: 2017/9/9
 * Time: 0:45
 * redis保存失败的数据放入队列中
 */
@Component
public class FailedRedisQueue {


    /**
     * 用户订单
     */
    public static ConcurrentLinkedDeque<UserOrderDetail> userOrderQueue = new ConcurrentLinkedDeque<>();
    /**
     * 订单
     */
    public static ConcurrentLinkedDeque<BaseOrderReq> baseOrderQueue = new ConcurrentLinkedDeque<>();
    /**
     * 返水
     */
    public static ConcurrentLinkedDeque<CashbackReq> cashbackQueue = new ConcurrentLinkedDeque<>();
    /**
     * 打赏
     */
    public static ConcurrentLinkedDeque<DealerRewardReq> dealerRewardQueue = new ConcurrentLinkedDeque<>();
    /**
     * 优惠赠送
     */
    public static ConcurrentLinkedDeque<DiscountReq> discountQueue = new ConcurrentLinkedDeque<>();
    /**
     * 彩金
     */
    public static ConcurrentLinkedDeque<JpReq> jpQueue = new ConcurrentLinkedDeque<>();
    /**
     * 线上入款
     */
    public static ConcurrentLinkedDeque<OnlChargeReq> onlChargeQueue = new ConcurrentLinkedDeque<>();
    /**
     * 人工入款
     */
    public static ConcurrentLinkedDeque<OperateChargeReq> operateChargeQueue = new ConcurrentLinkedDeque<>();
    /**
     * 人工提出
     */
    public static ConcurrentLinkedDeque<OperateWithDrawReq> operateWithDrawQueue = new ConcurrentLinkedDeque<>();
    /**
     * 公司入款
     */
    public static ConcurrentLinkedDeque<PreCmpChargeReq> preCmpChargeQueue = new ConcurrentLinkedDeque<>();
    /**
     * 会员出款
     */
    public static ConcurrentLinkedDeque<PreWithdrawReq> preWithdrawQueue = new ConcurrentLinkedDeque<>();
}
