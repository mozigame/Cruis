package com.magic.crius.service.impl;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.assemble.FailedRedisQueue;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.UserOrderDetailService;
import com.magic.crius.storage.db.UserOrderDetailDbService;
import com.magic.crius.storage.mongo.UserOrderDetailMongoService;
import com.magic.crius.storage.redis.UserOrderDetailRedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:30
 */
@Service
public class UserOrderDetailServiceImpl implements UserOrderDetailService {


    @Resource
    private UserOrderDetailDbService userOrderDetailDbService;
    @Resource
    private UserOrderDetailMongoService userOrderDetailMongoService;
    @Resource
    private UserOrderDetailRedisService userOrderDetailRedisService;

    @Override
    public boolean batchSave(List<UserOrderDetail> details) {
        return userOrderDetailDbService.batchSave(details);
    }

    @Override
    public boolean save(UserOrderDetail detail) {
        return userOrderDetailDbService.save(detail);
    }

    @Override
    public boolean updatePaid(UserOrderDetail detail) {
        return userOrderDetailDbService.updatePaid(detail);
    }

    @Override
    public boolean saveUpdateFailed(UserOrderDetail detail) {
        userOrderDetailMongoService.saveUpdateFailed(detail);
        if (!userOrderDetailRedisService.save(detail)) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ApiLogger.error("userOrder saveUpdateFailed false, detail : "+ JSON.toJSONString(detail));
            if (!userOrderDetailRedisService.save(detail)) {
                ApiLogger.error("retry userOrder saveUpdateFailed false, detail : "+ JSON.toJSONString(detail));
                FailedRedisQueue.userOrderQueue.add(detail);
            }
        }
        return true;
    }

    @Override
    public List<UserOrderDetail> batchPopRedis(Date date) {
        return userOrderDetailRedisService.batchPop(date);
    }

    @Override
    public List<UserOrderDetail> findByOrderId(UserOrderDetail detail) {
        return userOrderDetailDbService.findByOrderId(detail);
    }

    @Override
    public Map<Long, UserOrderDetail> findByOrderIds(List<UserOrderDetail> details) {
        List<UserOrderDetail> userOrderDetails = userOrderDetailDbService.findByOrderIds(details);
        if (userOrderDetails != null && userOrderDetails.size() > 0) {
            Map<Long, UserOrderDetail> map = new HashMap<>();
            userOrderDetails.forEach((order) ->{
                map.put(order.getOrderId(), order);
            });
            return map;
        }
        return null;
    }
}
