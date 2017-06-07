package com.magic.crius.assemble.impl;

import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.OwnerCompanyAccountSummmaryAssemService;
import com.magic.crius.assemble.OwnerCompanyFlowSummmaryAssemService;
import com.magic.crius.assemble.PreCmpChargeReqAssemService;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.enums.SummaryKind;
import com.magic.crius.po.OwnerCompanyAccountSummmary;
import com.magic.crius.po.OwnerCompanyFlowSummmary;
import com.magic.crius.service.PreCmpChargeReqService;
import com.magic.crius.util.CriusLog;
import com.magic.crius.vo.PreCmpChargeReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:32
 * 公司入款
 */
@Service
public class PreCmpChargeReqAssemServiceImpl implements PreCmpChargeReqAssemService {

    @Resource
    private PreCmpChargeReqService preCmpChargeService;
    @Resource
    private OwnerCompanyFlowSummmaryAssemService ownerCompanyFlowSummmaryAssemService;
    @Resource
    private OwnerCompanyAccountSummmaryAssemService ownerCompanyAccountSummmaryAssemService;

    @Override
    public void procKafkaData(PreCmpChargeReq req) {
        if (preCmpChargeService.getByReqId(req.getReqId()) == null) {
            if (!preCmpChargeService.savePreCmpCharge(req)) {
                CriusLog.error("save PreCmpCharge error,reqId : " + req.getReqId());
            }
        }
    }

    @Override
    public boolean convertData(Date date) {
        //TODO 在redis中获取list
        List<PreCmpChargeReq> list = preCmpChargeService.batchPopRedis(date);
        if (list != null && list.size() > 0) {
            Map<String, OwnerCompanyFlowSummmary> ownerCompanyFlowSummmaryMap = new HashMap<>();
            Map<Long, OwnerCompanyAccountSummmary> ownerCompanyAccountSummmaryMap = new HashMap<>();
            for (PreCmpChargeReq req : list) {
                if (ownerCompanyFlowSummmaryMap.get(req.getOwnerId() + "_" + req.getInBankNum()) == null) {
                    OwnerCompanyFlowSummmary flow = new OwnerCompanyFlowSummmary();
                    flow.setOwnerId(req.getOwnerId());
                    flow.setCompanyFlowMoneyCount(req.getAmount());
                    flow.setCompanyFlowNum(1);
                    flow.setAccountNum(req.getInBankNum());
                    flow.setAccountName(req.getBankHolder());
                    flow.setBankSystemCode(req.getInBankCode());
                    flow.setState(0);
                    flow.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(req.getProduceTime()), "yyyyMMdd")));
                    //TODO 暂时为空
                    flow.setAccountCode(0L);
                    flow.setBankSystemName("");
                    ownerCompanyFlowSummmaryMap.put(req.getOwnerId() + "" + req.getInBankNum(), flow);
                } else {
                    OwnerCompanyFlowSummmary flow = ownerCompanyFlowSummmaryMap.get(req.getOwnerId() + "" + req.getInBankNum());
                    flow.setCompanyFlowMoneyCount(flow.getCompanyFlowMoneyCount() + req.getAmount());
                    flow.setCompanyFlowNum(flow.getCompanyFlowNum() + 1);
                }
                if (ownerCompanyAccountSummmaryMap.get(req.getOwnerId()) == null) {
                    OwnerCompanyAccountSummmary account = new OwnerCompanyAccountSummmary();
                    account.setOwnerId(req.getOwnerId());
                    account.setSummaryMoneyCount(req.getAmount());
                    account.setSummaryUserNum(1);
                    //TODO 此处待确定
                    account.setSummaryType(111);
                    account.setSummaryTypeName("线上入款");
                    account.setSummaryKind(SummaryKind.income.value());
                    account.setPdate(Integer.parseInt(DateUtil.formatDateTime(new Date(), "yyyyMMdd")));
                    ownerCompanyAccountSummmaryMap.put(req.getOwnerId(), account);
                } else {
                    OwnerCompanyAccountSummmary account = ownerCompanyAccountSummmaryMap.get(req.getOwnerId());
                    account.setSummaryMoneyCount(account.getSummaryMoneyCount() + req.getAmount());
                    account.setSummaryUserNum(account.getSummaryUserNum() + 1);
                }
            }
            if (ownerCompanyFlowSummmaryMap.size() > 0) {
                ownerCompanyFlowSummmaryAssemService.batchSave(ownerCompanyFlowSummmaryMap);
            }
            if (ownerCompanyAccountSummmaryMap.size() > 0) {
                ownerCompanyAccountSummmaryAssemService.batchSave(ownerCompanyAccountSummmaryMap);
            }
            return list.size() >= RedisConstants.BATCH_POP_NUM;
        }
        return false;
    }
}
