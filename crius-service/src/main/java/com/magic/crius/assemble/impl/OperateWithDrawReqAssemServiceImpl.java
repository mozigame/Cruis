package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OperateWithDrawReqAssemService;
import com.magic.crius.assemble.OwnerOperateOutDetailAssemService;
import com.magic.crius.service.OperateWithDrawReqService;
import com.magic.crius.vo.OperateWithDrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public void procKafkaData(OperateWithDrawReq req) {
        if (operateWithDrawReqService.getByReqId(req.getReqId()) == null) {
            if (!operateWithDrawReqService.save(req)) {
                //todo
            }
        }
    }

}
