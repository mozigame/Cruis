package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.PreWithdrawReqAssemService;
import com.magic.crius.assemble.UserOutMoneySummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:32
 * 用户提现
 */
@Service
public class PreWithdrawReqAssemServiceImpl implements PreWithdrawReqAssemService {

    @Resource
    private PreWithdrawReqService preWithdrawService;
    @Resource
    private UserOutMoneySummaryAssemService userOutMoneySummaryAssemService;

    @Override
    public void procKafkaData(PreWithdrawReq req) {
        if (preWithdrawService.getByReqId(req.getReqId()) == null) {
            if (!preWithdrawService.save(req)) {
                CriusLog.error("save PreWithdrawReq error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        List<PreWithdrawReq> list = preWithdrawService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            Map<String, UserOutMoneySummary> userOutMoneySummaryMap = new HashMap<>();
            for (PreWithdrawReq req : list) {
                if (userOutMoneySummaryMap.get(req.getOwnerId() + "_" + req.getUserId()) == null) {
                    UserOutMoneySummary summary = new UserOutMoneySummary();
                    summary.setOwnerId(req.getOwnerId());
                    summary.setUserId(req.getUserId());
                    summary.setOrderCount(req.getAmount());
                    summary.setOutNum(1);
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    userOutMoneySummaryMap.put(req.getOwnerId() + "_" + req.getUserId(), summary);
                } else {
                    UserOutMoneySummary summary = userOutMoneySummaryMap.get(req.getOwnerId() + "_" + req.getUserId());
                    summary.setOrderCount(summary.getOrderCount() + req.getAmount());
                    summary.setOutNum(summary.getOutNum() + 1);
                }
            }
            if (userOutMoneySummaryMap.size() > 0) {
                userOutMoneySummaryAssemService.batchSave(userOutMoneySummaryMap.values());
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
