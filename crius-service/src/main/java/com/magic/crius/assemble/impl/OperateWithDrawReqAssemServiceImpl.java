package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OperateWithDrawReqAssemService;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.OwnerOperateOutDetail;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:49
 * 人工提现
 */
@Service
public class OperateWithDrawReqAssemServiceImpl implements OperateWithDrawReqAssemService {

    @Resource
    private OperateWithDrawReqService operateWithDrawReqService;
    /*人工出款详情*/
    @Resource
    private OwnerOperateOutDetailAssemService ownerOperateOutDetailAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;

    @Override
    public void procKafkaData(OperateWithDrawReq req) {
        if (operateWithDrawReqService.getByReqId(req.getReqId()) == null) {
            if (!operateWithDrawReqService.save(req)) {
                //todo
            }
        }
    }

}
