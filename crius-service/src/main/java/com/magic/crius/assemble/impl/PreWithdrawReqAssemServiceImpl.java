package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.PreWithdrawReqAssemService;
import com.magic.crius.assemble.UserAccountSummaryAssemService;
import com.magic.crius.assemble.UserOutMoneyDetailAssemService;
import com.magic.crius.assemble.UserOutMoneySummaryAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.UserAccountSummary;
import com.magic.crius.po.UserOutMoneyDetail;
import com.magic.crius.po.UserOutMoneySummary;
import com.magic.crius.service.PreWithdrawReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:32
 * 用户提现
 */
@Service
public class PreWithdrawReqAssemServiceImpl implements PreWithdrawReqAssemService {
    /**
     * 用户提现
     */
    @Resource
    private PreWithdrawReqService preWithdrawService;
    /**
     * 会员出款汇总
     */
    @Resource
    private UserOutMoneySummaryAssemService userOutMoneySummaryAssemService;
    /*会员出款明细*/
    @Resource
    private UserOutMoneyDetailAssemService userOutMoneyDetailAssemService;
    /*会员账号汇总*/
    @Resource
    private UserAccountSummaryAssemService userAccountSummaryAssemService;

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
            Map<Long, UserOutMoneySummary> userOutMoneySummaryMap = new HashMap<>();
            List<UserOutMoneyDetail> details = new ArrayList<>();
            Map<Long, UserAccountSummary> userAccountSummaryMap = new HashMap<>();

            for (PreWithdrawReq req : list) {
                /*会员出款汇总*/
                if (userOutMoneySummaryMap.get(req.getUserId()) == null) {
                    UserOutMoneySummary summary = new UserOutMoneySummary();
                    summary.setOwnerId(req.getOwnerId());
                    summary.setUserId(req.getUserId());
                    summary.setOrderCount(req.getAmount());
                    //todo 提现次数
                    summary.setOutNum(1);
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    userOutMoneySummaryMap.put(req.getUserId(), summary);
                } else {
                    UserOutMoneySummary summary = userOutMoneySummaryMap.get(req.getUserId());
                    summary.setOrderCount(summary.getOrderCount() + req.getAmount());
                    summary.setOutNum(summary.getOutNum() + 1);
                }

                /*会员出款明细*/
                UserOutMoneyDetail detail = new UserOutMoneyDetail();
                detail.setOwnerId(req.getOwnerId());
                detail.setUserId(req.getUserId());
                detail.setOrderCount(req.getAmount());
                //TODO 待定
                detail.setTaxCount(0L);
                //TODO 待定
                detail.setState(0);
                //TODO 待定
                detail.setOrderId(0L);
                detail.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                //TODO 待定
                detail.setHandlerId(0L);
                //TODO 待定
                detail.setHandlerName("");
                detail.setCreateTime(req.getProduceTime());
                detail.setUpdateTime(req.getProduceTime());
                details.add(detail);

                /*会员账号汇总*/
                if (userAccountSummaryMap.get(req.getUserId()) == null) {
                    UserAccountSummary summary = new UserAccountSummary();
                    summary.setUserId(req.getUserId());
                    //todo 提现次数
                    summary.setOutNum(1L);
                    summary.setOutCount(req.getAmount());

                    summary.setFlowNum(0L);
                    summary.setFlowCount(0L);
                    summary.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                    userAccountSummaryMap.put(req.getUserId(), summary);
                } else {
                    UserAccountSummary summary = userAccountSummaryMap.get(req.getUserId());
                    summary.setOutNum(summary.getOutNum() + 1);
                    summary.setOutCount(summary.getOutCount() + req.getAmount());
                }

            }
            if (userOutMoneySummaryMap.size() > 0) {
                userOutMoneySummaryAssemService.batchSave(userOutMoneySummaryMap);
            }
            if (details.size() > 0) {
                userOutMoneyDetailAssemService.batchSave(details);
            }
            if (userAccountSummaryMap.size() > 0) {
                userAccountSummaryAssemService.updateWithdraw(userAccountSummaryMap);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
