package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.DiscountReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.util.PropertiesLoad;
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
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkMongoResId()) {
                if (discountReqService.getByReqId(req) == null) {
                    if (!discountReqService.save(req)) {
                        logger.error("save Discount error,reqId : " + req.getReqId());
                    }
                }else {
                    logger.warn("save DiscountReq failed, reqId has exist, reqId : "+ req.getReqId());
                }
            } else {
                if (!discountReqService.save(req)) {
                    logger.error("save Discount error,reqId : " + req.getReqId());
                }
            }
        } else {
            logger.warn("reqId is null,"+ JSON.toJSONString(req));
        }

    }

}
