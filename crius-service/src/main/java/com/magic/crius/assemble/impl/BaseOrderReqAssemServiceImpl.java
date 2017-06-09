package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.BaseOrderReqAssemService;
import com.magic.crius.assemble.UserOrderDetailAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:20
 */
@Service
public class BaseOrderReqAssemServiceImpl implements BaseOrderReqAssemService {

    @Resource
    private BaseOrderReqService baseGameReqService;
    @Resource
    private UserOrderDetailAssemService userOrderDetailAssemService;

    @Override
    public void procKafkaData(BaseOrderReq req) {
        if (baseGameReqService.getByReqId(req.getReqId()) == null) {
            if (!baseGameReqService.save(req)) {
                //todo
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<BaseOrderReq> list = baseGameReqService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            List<UserOrderDetail> details = new ArrayList<>();
            for (BaseOrderReq req : list) {
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
                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                detail.setCreateTime(req.getProduceTime());
                detail.setUpdateTime(req.getProduceTime());
                detail.setOrderExtent(req.getOrderExtent().toJSONString());
                details.add(detail);
            }
            if (details.size() > 0) {
                userOrderDetailAssemService.batchSave(details);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
