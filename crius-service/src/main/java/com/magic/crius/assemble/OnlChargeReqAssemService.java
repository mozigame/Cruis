package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.crius.service.OnlChargeReqService;
import com.magic.crius.util.PropertiesLoad;
import com.magic.crius.vo.OnlChargeReq;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 * 用户充值成功
 */
@Service
public class OnlChargeReqAssemService  {

    private static final Logger logger = Logger.getLogger(BaseOrderReqAssemService.class);

    @Resource
    private OnlChargeReqService onlChargeService;

    public void procKafkaData(OnlChargeReq req) {
        if (req.getReqId() != null) {
            if (PropertiesLoad.checkMongoResId()) {
                if (onlChargeService.getByReqId(req.getReqId()) == null) {
                    if (!onlChargeService.save(req)) {
                        logger.error("save OnlChargeReq error,reqId : " + req.getReqId());
                    }
                }else {
                    logger.warn("save OnlChargeReq failed, reqId has exist, reqId : "+ req.getReqId());
                }
            } else {
                if (!onlChargeService.save(req)) {
                    logger.error("save OnlChargeReq error,reqId : " + req.getReqId());
                }
            }
        } else {
            logger.warn("reqId is null,"+ JSON.toJSONString(req));
        }
    }

}
