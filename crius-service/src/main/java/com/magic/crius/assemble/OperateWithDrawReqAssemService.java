package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.util.PropertiesLoad;
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
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkMongoResId()) {
                if (operateWithDrawReqService.getByReqId(req.getReqId()) == null) {
                    if (!operateWithDrawReqService.save(req)) {
                        logger.error("save OperateWithDrawReq error,reqId : " + req.getReqId());
                    }
                }else {
                    logger.warn("save OperateWithDrawReq failed, reqId has exist, reqId : "+ req.getReqId());
                }
            } else {
                if (!operateWithDrawReqService.save(req)) {
                    logger.error("save OperateWithDrawReq error,reqId : " + req.getReqId());
                }
            }
        } else {
            logger.warn("reqId is null,"+ JSON.toJSONString(req));
        }
    }


}
