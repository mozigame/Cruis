package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.enums.IsPaidType;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.BaseOrderReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:20
 */
@Service
public class BaseOrderReqAssemService {

    private static final Logger logger = Logger.getLogger(BaseOrderReqAssemService.class);
    @Resource
    private BaseOrderReqService baseOrderReqService;

    public void procKafkaData(BaseOrderReq req) {
        //todo 默认为未派彩，之后修复
        req.setIsPaid(IsPaidType.noPaid.value());
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkOrderMongoResId()) {
                logger.info("save BaseOrderReq checkReqId : "+ req.getReqId());
                if (baseOrderReqService.getByReqId(req.getReqId()) == null) {
                    if (!baseOrderReqService.save(req)) {
                        logger.error("save BaseOrderReq error,reqId : " + req.getReqId());
                    }
                }
            } else {
                if (!baseOrderReqService.save(req)) {
                    logger.error("save BaseOrderReq error,reqId : " + req.getReqId());
                }
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
