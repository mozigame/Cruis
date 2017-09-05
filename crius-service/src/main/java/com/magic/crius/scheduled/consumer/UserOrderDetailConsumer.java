package com.magic.crius.scheduled.consumer;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.TethysUserOrderDetailService;
import com.magic.crius.service.UserOrderDetailService;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.user.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * User: joey
 * Date: 2017/9/2
 * Time: 22:19
 */
@Component
public class UserOrderDetailConsumer {

    private ExecutorService executorService = ThreadTaskPoolFactory.coreThreadTaskPool;

    @Resource
    private UserOrderDetailService userOrderDetailService;
    @Resource
    private TethysUserOrderDetailService tethysUserOrderDetailService;

    /**
     * 初始化定时任务
     * @param date
     */
    public void init(Date date) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                currentDataCalculate(date);
            }
        });

    }

    /**
     * 开始执行派彩修改失败的订单
     * @param date
     */
    private void currentDataCalculate(Date date) {
        List<UserOrderDetail> details = userOrderDetailService.batchPopRedis(date);
        details.forEach((UserOrderDetail detail)->{
            if (!userOrderDetailService.updatePaid(detail)) {
                List<UserOrderDetail> details_ = userOrderDetailService.findByOrderId(detail);
                if (details_ == null || details_.size() == 0) {
                    userOrderDetailService.save(detail);
                } else {
                    boolean flag = false;
                    for (UserOrderDetail detail_ : details_) {
                        if (detail_.getIsPaid() == IsPaidType.paid.value()) {
                            flag =true;
                            break;
                        }
                    }
                    if (!flag) {
                        ApiLogger.error("crius user order consumer currentDataCalculate failed, detail : "+ JSON.toJSONString(detail));
                    }
                }
            }
            if (!tethysUserOrderDetailService.updatePaid(detail)) {
                List<UserOrderDetail> details_ = tethysUserOrderDetailService.findByOrderId(detail);
                if (details_ == null || details_.size() == 0) {
                    tethysUserOrderDetailService.save(detail);
                } else {
                    boolean flag = false;
                    for (UserOrderDetail detail_ : details_) {
                        if (detail_.getIsPaid() == IsPaidType.paid.value()) {
                            flag =true;
                            break;
                        }
                    }
                    if (!flag) {
                        ApiLogger.error("tethys user order consumer currentDataCalculate failed, detail : "+ JSON.toJSONString(detail));
                    }
                }
            }
        });
    }


}
