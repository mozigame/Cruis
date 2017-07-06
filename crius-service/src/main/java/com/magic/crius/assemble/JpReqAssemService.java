package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.JpReqService;
import com.magic.crius.vo.JpReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:50
 */
@Service
public class JpReqAssemService {

    private static final Logger logger = Logger.getLogger(JpReqAssemService.class);

    @Resource
    private JpReqService jpReqService;

    public void procKafkaData(JpReq req) {
        if (req.getReqId() != null && jpReqService.getByReqId(req.getReqId()) == null) {
            if (!jpReqService.save(req)) {
                logger.error("save JpReq error,reqId : " + req.getReqId());
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
