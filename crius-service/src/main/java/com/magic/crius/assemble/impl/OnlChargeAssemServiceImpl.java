package com.magic.crius.assemble.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.assemble.OnlChargeAssemService;
import com.magic.crius.service.OnlChargeService;
import com.magic.crius.service.PreCmpChargeService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.Parent;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:31
 */
@Service("onlChargeAssembleService")
public class OnlChargeAssemServiceImpl implements OnlChargeAssemService {


    @Resource
    private OnlChargeService onlChargeService;

    @Override
    public void procKafkaData(OnlChargeReq req) {
        if (onlChargeService.getByReqId(req.getReqId()) == null) {
            if (!onlChargeService.save(req)) {
                CriusLog.error("save OnlChargeReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        return false;
    }
}
