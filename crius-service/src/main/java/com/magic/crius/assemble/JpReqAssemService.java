package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.JpReqAssemService;
import com.magic.crius.assemble.PrizeDetailAssemService;
import com.magic.crius.po.PrizeDetail;
import com.magic.crius.service.JpReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.JpReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:50
 */
@Service
public class JpReqAssemService {


    @Resource
    private JpReqService jpReqService;
    @Resource
    private PrizeDetailAssemService prizeDetailAssemService;

    public void procKafkaData(JpReq req) {
        if (req.getReqId() != null && jpReqService.getByReqId(req.getReqId()) == null) {
            if (!jpReqService.save(req)) {
                CriusLog.error("save JpReq error,reqId : " + req.getReqId());
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
