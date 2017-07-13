package com.magic.crius.service.impl;

import javax.annotation.Resource;

import com.magic.analysis.utils.StringUtils;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.po.ProxyBillSummary2game;
import org.springframework.stereotype.Service;

import com.magic.crius.po.BillInfo;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.storage.db.BillInfoDbService;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.OwnerBillReq;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:17
 */
@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Resource
    private BillInfoDbService billInfoDbService;

    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoDbService.save(billInfo);
    }
    
    public void save(OwnerBillReq req){
    	
    	/*List<>  s=req.getHallBillInfos();
    	List<>  a=req.getOwnerCostInfo();*/
    }
    
    public void save(AgentBillReq req){
    	
    }


    private BillInfo assembleBillInfo(AgentBillReq agentBillReq){
        BillInfo billInfo = new BillInfo();
        billInfo.setOwnerId(agentBillReq.getOwnerId());
        billInfo.setProxyId(agentBillReq.getAgentId());
        billInfo.setOrderId(agentBillReq.getBillId().toString());
        billInfo.setOrderName("");
        if (StringUtils.isStringNull(agentBillReq.getBillDate())){
            billInfo.setPdate(Integer.parseInt(agentBillReq.getBillDate()));
        }
        billInfo.setSchemeCode(agentBillReq.getSchemeId().toString());
        billInfo.setSchemeName(agentBillReq.getSchemeName());
        billInfo.setAccount(agentBillReq.getCostTotalAmount());
        billInfo.setIncome(agentBillReq.getVaildBettTotalAmount());
        billInfo.setBillType(2);//1:业主;2:代理
        billInfo.setStartTime(agentBillReq.getBillStartTime());
        billInfo.setEndTime(agentBillReq.getBillEndTime());
        billInfo.setPdateName(agentBillReq.getBillDate());
        billInfo.setStatus(1);//1:未处理
        return billInfo;
    }

    private ProxyBillDetail assembleProxyBillDetail(AgentBillReq agentBillReq){
        ProxyBillDetail proxyBillDetail = new ProxyBillDetail();
        proxyBillDetail.setOwnerId(agentBillReq.getOwnerId());
        proxyBillDetail.setProxyId(agentBillReq.getAgentId());
        proxyBillDetail.setOrderId(agentBillReq.getBillId());
        proxyBillDetail.setPdate(agentBillReq.getBillDate());
        //proxyBillDetail.setUserNum();;
        proxyBillDetail.setIncome(agentBillReq.getPayoffTotalAmount());
        proxyBillDetail.setRecordReforwardAccount(agentBillReq.getRebateTotalAmount());
        proxyBillDetail.setEffectOrderCount(agentBillReq.getVaildBettTotalAmount());
        proxyBillDetail.setCost(agentBillReq.getCostTotalAmount());
        proxyBillDetail.setReforwardState(1);
        return proxyBillDetail;
    }

    private ProxyBillSummary2game assembleProxyBillSummary2Game(AgentBillReq agentBillReq){
        ProxyBillSummary2game proxyBillSummary2game = new ProxyBillSummary2game();
        proxyBillSummary2game.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2game.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2game.setOrderId(agentBillReq.getBillId().toString());
        proxyBillSummary2game.setEffectOrderCount(agentBillReq.getVaildBettTotalAmount());

        proxyBillSummary2game.setIncome(agentBillReq.getPayoffTotalAmount());
        if (StringUtils.isStringNull(agentBillReq.getBillDate())){
            proxyBillSummary2game.setPdate(Integer.parseInt(agentBillReq.getBillDate()));
        }
        return proxyBillSummary2game;
    }


    private ProxyBillSummary2cost assembleProxyBillSummary2Cost(AgentBillReq agentBillReq){
        ProxyBillSummary2cost proxyBillSummary2cost = new ProxyBillSummary2cost();
        proxyBillSummary2cost.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2cost.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2cost.setOrderId(agentBillReq.getBillId().toString());
        if (StringUtils.isStringNull(agentBillReq.getBillDate())){
            proxyBillSummary2cost.setPdate(Integer.parseInt(agentBillReq.getBillDate()));
        }
        proxyBillSummary2cost.setCost(agentBillReq.getCostTotalAmount());
        return proxyBillSummary2cost;
    }


}
