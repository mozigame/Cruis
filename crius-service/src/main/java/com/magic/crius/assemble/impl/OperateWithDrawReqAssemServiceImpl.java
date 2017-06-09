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

    @Override
    public boolean convertData(Date date) {
//        List<OperateWithDrawReq> list = operateWithDrawReqService.batchPopRedis(date);
//        if (list != null && list.size() > 0) {
//            Map<String, OwnerOperateOutDetail> ownerOperateOutDetailMap = new HashMap<>();
//            Map<Long, UserAccountSummary> userAccountSummaryMap = new HashMap<>();
//
//            for (OperateWithDrawReq req : list) {
//                /*人工出款详情*/
//                OwnerOperateOutDetail detail = new OwnerOperateOutDetail();
//                detail.setOwnerId(req.getOwnerId());
//                detail.setOperateOutMoneyCount(req.getAmount());
//                //TODO 出款次数
//                detail.setOperateOutNum(req.getUserIds().length);
//                detail.setOperateOutType(req.getWithdrawType());
//                detail.setOperateOutTypeName(req.getRemark());
//                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
//                ownerOperateOutDetailMap.put(req.getOwnerId() + "_" + req.getWithdrawType(), detail);
//
//                /*会员账号汇总*/
//                if (req.getUserIds() != null && req.getUserIds().length > 0) {
//                    for (Long userId : req.getUserIds()) {
//                        if (userAccountSummaryMap.get(userId) == null) {
//                            UserAccountSummary summary = new UserAccountSummary();
//                            summary.setUserId(userId);
//                            //TODO 出款次数
//                            summary.setOutNum(1L);
//                            summary.setOutCount(req.getAmount());
//                            summary.setFlowNum(0L);
//                            summary.setFlowCount(0L);
//                            summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
//                            userAccountSummaryMap.put(userId, summary);
//                        } else {
//                            UserAccountSummary summary = userAccountSummaryMap.get(userId);
//                            //TODO 出款次数
//                            summary.setOutNum(summary.getOutNum() + 1);
//                            summary.setOutCount(summary.getOutCount() + req.getAmount());
//                        }
//                    }
//                }
//            }
//            if (ownerOperateOutDetailMap.size() > 0) {
//                ownerOperateOutDetailAssemService.batchSave(ownerOperateOutDetailMap);
//            }
//            if (userAccountSummaryMap.size() > 0) {
//                userAccountSummaryAssemService.updateWithdraw(userAccountSummaryMap);
//            }
//            return list.size() >= RedisConstants.BATCH_POP_NUM;
//        }
        return false;
    }
}
