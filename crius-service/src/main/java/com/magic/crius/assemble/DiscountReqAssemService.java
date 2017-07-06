package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.DiscountReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:40
 * 优惠赠送
 */
@Service
public class DiscountReqAssemService  {

    private static final Logger logger = Logger.getLogger(DiscountReqAssemService.class);
    @Resource
    private DiscountReqService discountReqService;

    public void procKafkaData(DiscountReq req) {
        if (req.getReqId() != null && discountReqService.getByReqId(req.getReqId()) == null) {
            if (!discountReqService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
