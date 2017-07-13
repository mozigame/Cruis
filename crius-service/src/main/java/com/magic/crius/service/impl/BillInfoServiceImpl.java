package com.magic.crius.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.analysis.utils.StringUtils;
import com.magic.crius.po.BillInfo;
import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.po.ProxyBillSummary2game;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.service.OwnerBillSummary2costService;
import com.magic.crius.service.OwnerBillSummary2gameService;
import com.magic.crius.service.ProxyBillDetailService;
import com.magic.crius.service.ProxyBillSummary2costService;
import com.magic.crius.service.ProxyBillSummary2gameService;
import com.magic.crius.storage.db.BillInfoDbService;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.AgentHallBillVo;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.OwnerHallBillVo;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:17
 */
@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Resource
    private BillInfoDbService billInfoDbService;

    @Resource
    private ProxyBillDetailService proxyBillDetailService;

    @Resource
    private ProxyBillSummary2gameService proxyBillSummary2gameService;

    @Resource
    private ProxyBillSummary2costService proxyBillSummary2costService;
    

    @Resource
    private OwnerBillSummary2costService ownerBillSummary2costService;
    
    @Resource
    private OwnerBillSummary2gameService ownerBillSummary2gameService;


    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoDbService.save(billInfo);
    }
    
    public void save(OwnerBillReq req){
    	if(req==null){
    		return;
    	}
    	
    	BillInfo billInfo=this.assembleBillInfo(req);
    	List<OwnerBillSummary2cost> costList=this.assembleBillInfoSummaryCost(req);
    	List<OwnerBillSummary2game> gameList=this.assembleBillInfoSummaryGame(req);
    	this.save(billInfo);
    	if(costList.size()>0){
    		this.ownerBillSummary2costService.batchInsert(costList);
    	}
    	if(gameList.size()>0){
    		this.ownerBillSummary2gameService.batchInsert(gameList);
    	}
    }
    
    public void save(AgentBillReq req){
        if (req != null) {
            BillInfo billInfo = assembleBillInfo(req);
            ProxyBillDetail proxyBillDetail = assembleProxyBillDetail(req);
            ProxyBillSummary2game proxyBillSummary2game = assembleProxyBillSummary2Game(req);
            List<ProxyBillSummary2cost> proxyBillSummary2cost = assembleProxyBillSummary2Cost(req);

            billInfoDbService.save(billInfo);
            proxyBillDetailService.save(proxyBillDetail);
            proxyBillSummary2gameService.save(proxyBillSummary2game);
            proxyBillSummary2costService.batchInsert(proxyBillSummary2cost);
        }
    }
    
    private List<OwnerBillSummary2cost> assembleBillInfoSummaryCost(OwnerBillReq req){
    	List<OwnerBillSummary2cost> costList=new ArrayList<>();
    	OwnerBillSummary2cost cost=null;
    	for(AmountVo amount:req.getOwnerCostInfo()){
    		cost = new OwnerBillSummary2cost();
    		cost.setOwnerId(req.getOwnerId());
    		cost.setOrderId(String.valueOf(req.getBillId()));
//    		cost.setProxyId();
    		cost.setBill(amount.getAmount());
    		cost.setBillType(String.valueOf(amount.getAmountTypeId()));
    		cost.setBillTypeName(amount.getAmountTypeName());
    		cost.setCost(amount.getAmount());
    		costList.add(cost);
    	}
        return costList;
    }
    
    private List<OwnerBillSummary2game> assembleBillInfoSummaryGame(OwnerBillReq req){
    	List<OwnerBillSummary2game> gameList=new ArrayList<>();
    	OwnerBillSummary2game game=null;
    	String gameType=null;
    	for(OwnerHallBillVo bill:req.getHallBillInfos()){
    		game = new OwnerBillSummary2game();
    		game.setOwnerId(req.getOwnerId());
    		game.setOrderId(String.valueOf(req.getBillId()));
    		game.setPdate(req.getBillDate());
    		game.setPdateName(req.getBillDate());
            gameType=this.getFactoryGameType(bill.getPlatformId(), bill.getHallTypeId());
            game.setGameType(gameType);
            game.setIncome(bill.getPayOffAmount());
            game.setBillCount(bill.getNeedPayAmount());
            gameList.add(game);
    	}
    	
        return gameList;
    }
    
    private String getFactoryGameType(Long gameFactoryType, Long gameAbstractType) {
    	 //@TODO
		return null;
	}

	private BillInfo assembleBillInfo(OwnerBillReq req){
    	BillInfo billInfo = new BillInfo();
        billInfo.setOwnerId(req.getOwnerId());
//        billInfo.setProxyId(req);
        billInfo.setOrderId(String.valueOf(req.getBillId()));
        if (StringUtils.isStringNull(req.getBillDate())){
            billInfo.setPdate(Integer.parseInt(req.getBillDate()));
        }
        billInfo.setOrderName(billInfo.getPdate()+"账单");
        billInfo.setSchemeCode(String.valueOf(req.getSchemeId()));
        billInfo.setSchemeName(req.getSchemeName());
        billInfo.setAccount(req.getTotalCost());
        billInfo.setIncome(req.getRealToalCost());
        billInfo.setBillType(1);//1:业主;2:代理
        billInfo.setStartTime(req.getBillStartTime());
        billInfo.setEndTime(req.getBillEndTime());
        billInfo.setPdateName(req.getBillDate());
        billInfo.setStatus(1);//1:未处理
        return billInfo;
    }


    private BillInfo assembleBillInfo(AgentBillReq agentBillReq){
        BillInfo billInfo = new BillInfo();
        billInfo.setOwnerId(agentBillReq.getOwnerId());
        billInfo.setProxyId(agentBillReq.getAgentId());
        billInfo.setOrderId(agentBillReq.getBillId().toString());
        billInfo.setOrderName(agentBillReq.getBillDate()+"月账单");
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
        proxyBillDetail.setReforwardAccount(agentBillReq.getRebateTotalAmount());
        proxyBillDetail.setEffectOrderCount(agentBillReq.getVaildBettTotalAmount());
        proxyBillDetail.setCost(agentBillReq.getCostTotalAmount());
        proxyBillDetail.setReforwardState(1);
        proxyBillDetail.setRecordReforwardAccount(agentBillReq.getRebateTotalAmount());
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
        List<AgentHallBillVo> agentHallBillVoList = agentBillReq.getAgentHallInfos();
        Long cashbackAmount = 0L;
        Long costAmount = 0L;
        Long rebateAmount = 0L;
        for ( AgentHallBillVo agentHallBillVo : agentHallBillVoList) {
            cashbackAmount += agentHallBillVo.getCashbackAmount();
            costAmount += agentHallBillVo.getCostAmount();
            rebateAmount += agentHallBillVo.getRebateAmount();
        }
        proxyBillSummary2game.setReforward(cashbackAmount);
        proxyBillSummary2game.setAdministration(costAmount);
        proxyBillSummary2game.setReforwardAccount(rebateAmount);

        return proxyBillSummary2game;
    }


    private List<ProxyBillSummary2cost> assembleProxyBillSummary2Cost(AgentBillReq agentBillReq){
        List<ProxyBillSummary2cost> proxyBillSummary2costList = new ArrayList<>();

        ProxyBillSummary2cost proxyBillSummary2cost = new ProxyBillSummary2cost();
        proxyBillSummary2cost.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2cost.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2cost.setOrderId(agentBillReq.getBillId().toString());
        if (StringUtils.isStringNull(agentBillReq.getBillDate())){
            proxyBillSummary2cost.setPdate(Integer.parseInt(agentBillReq.getBillDate()));
        }
        proxyBillSummary2cost.setCostType("1");//手续费
        proxyBillSummary2cost.setCostTypeName("手续费");
        proxyBillSummary2cost.setCost(agentBillReq.getFeeAmount());
        proxyBillSummary2costList.add(proxyBillSummary2cost);

        ProxyBillSummary2cost proxyBillSummary2cost_1 = new ProxyBillSummary2cost();
        proxyBillSummary2cost_1.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2cost_1.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2cost_1.setOrderId(agentBillReq.getBillId().toString());
        if (StringUtils.isStringNull(agentBillReq.getBillDate())){
            proxyBillSummary2cost_1.setPdate(Integer.parseInt(agentBillReq.getBillDate()));
        }
        proxyBillSummary2cost_1.setCostType("2");//手续费
        proxyBillSummary2cost_1.setCostTypeName("优惠");
        proxyBillSummary2cost_1.setCost(agentBillReq.getDiscountAmount());
        proxyBillSummary2costList.add(proxyBillSummary2cost_1);

        return proxyBillSummary2costList;
    }


}
