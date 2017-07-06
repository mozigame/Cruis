package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:49
 * 人工提现
 */
@Service
public class OperateWithDrawReqAssemService  {

    private static final Logger logger = Logger.getLogger(OperateWithDrawReqAssemService.class);
    @Resource
    private OperateWithDrawReqService operateWithDrawReqService;

    public void procKafkaData(OperateWithDrawReq req) {
        if (req.getReqId() != null && operateWithDrawReqService.getByReqId(req.getReqId()) == null) {
            if (!operateWithDrawReqService.save(req)) {
                //todo
            }
        } else {
            logger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
