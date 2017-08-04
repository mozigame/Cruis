package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.service.BaseOrderReqService;
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
        if (req.getReqId() != null) {
            if (!baseOrderReqService.save(req)) {
                //todo
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
