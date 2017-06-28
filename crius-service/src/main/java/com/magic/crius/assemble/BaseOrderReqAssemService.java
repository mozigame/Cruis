package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.crius.service.BaseOrderReqService;
import com.magic.crius.vo.BaseOrderReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 20:20
 */
@Service
public class BaseOrderReqAssemService {

    @Resource
    private BaseOrderReqService baseOrderReqService;

    public void procKafkaData(BaseOrderReq req) {
        if (req.getReqId() != null && baseOrderReqService.getByReqId(req.getReqId()) == null) {
            if (!baseOrderReqService.save(req)) {
                //todo
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }

}
