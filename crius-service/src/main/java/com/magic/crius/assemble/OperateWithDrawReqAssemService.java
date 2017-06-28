package com.magic.crius.assemble;

import com.alibaba.fastjson.JSON;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateWithDrawReqAssemService;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.assemble.UserTradeAssemService;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.UserTrade;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:49
 * 人工提现
 */
@Service
public class OperateWithDrawReqAssemService  {


    @Resource
    private OperateWithDrawReqService operateWithDrawReqService;

    public void procKafkaData(OperateWithDrawReq req) {
        if (req.getReqId() != null && operateWithDrawReqService.getByReqId(req.getReqId()) == null) {
            if (!operateWithDrawReqService.save(req)) {
                //todo
            }
        } else {
            ApiLogger.warn("data not matching,"+ JSON.toJSONString(req));
        }
    }


}
