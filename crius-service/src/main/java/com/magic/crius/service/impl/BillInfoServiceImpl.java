package com.magic.crius.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.ApiLogger;
import com.magic.commons.enginegw.service.ThriftFactory;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGHeader;
import com.magic.config.thrift.base.EGReq;
import com.magic.config.thrift.base.EGResp;
import com.magic.user.vo.AgentConfigVo;
import org.springframework.stereotype.Service;

import com.magic.analysis.utils.StringUtils;
import com.magic.crius.po.BillInfo;
import com.magic.crius.po.OwnerBillSummary2cost;
import com.magic.crius.po.OwnerBillSummary2game;
import com.magic.crius.po.ProxyBillDetail;
import com.magic.crius.po.ProxyBillSummary2cost;
import com.magic.crius.po.ProxyBillSummary2game;
import com.magic.crius.po.UserInfo;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.service.OwnerBillSummary2costService;
import com.magic.crius.service.OwnerBillSummary2gameService;
import com.magic.crius.service.ProxyBillDetailService;
import com.magic.crius.service.ProxyBillSummary2costService;
import com.magic.crius.service.ProxyBillSummary2gameService;
import com.magic.crius.service.UserInfoService;
import com.magic.crius.storage.db.BillInfoDbService;
import com.magic.crius.storage.mongo.AgentBillReqMongoService;
import com.magic.crius.storage.mongo.OwnerBillReqMongoService;
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
    private OwnerBillReqMongoService ownerBillReqMongoService;
    @Resource
    private AgentBillReqMongoService agentBillReqMongoService;

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
    
    @Resource
    private GameInfoService gameInfoService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private ThriftFactory thriftFactory;

    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoDbService.save(billInfo);
    }


    @Override
    public boolean isExistBill(BillInfo billInfo) {
        return billInfoDbService.isExistBillInfo(billInfo);
    }

    public void save(OwnerBillReq req){
    	if(req==null){
    		return;
    	}
    	
    	if (!ownerBillReqMongoService.save(req)) {
    		ownerBillReqMongoService.saveFailedData(req);
            //todo
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
        	if (!agentBillReqMongoService.save(req)) {
        		agentBillReqMongoService.saveFailedData(req);
                //todo
            }
        	
            BillInfo billInfo = assembleBillInfo(req);
            ProxyBillDetail proxyBillDetail = assembleProxyBillDetail(req);
            List<ProxyBillSummary2game> proxyBillSummary2game = assembleProxyBillSummary2Game(req);
            List<ProxyBillSummary2cost> proxyBillSummary2cost = assembleProxyBillSummary2Cost(req);

            billInfoDbService.save(billInfo);
            proxyBillDetailService.save(proxyBillDetail);
            proxyBillSummary2gameService.batchInsert(proxyBillSummary2game);
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
//            gameType=this.getFactoryGameType(bill.getPlatformId(), bill.getHallTypeId());
    		//gameType=String.valueOf(bill.getPlatformId());
            gameType=this.getFactoryGameType(bill.getPlatformId(), bill.getHallTypeId());
            game.setGameType(gameType);
            game.setGameType(gameType);
            game.setIncome(bill.getPayOffAmount());
            game.setBillCount(bill.getNeedPayAmount());
            gameList.add(game);
    	}
    	
        return gameList;
    }
    
    private String getFactoryGameType(Long platformId, Long hallTypeId) {
   	    /*String key=gameFactoryType+"-"+gameAbstractType;
    	return gameInfoService.getGameTypeByFactoryMap().get(key);*/
        String gameType = gameInfoService.getGameType(platformId+"",hallTypeId+"");
        ApiLogger.info("platformId : " + platformId + " ; hallTypeId : " + hallTypeId + " ; gameType: " + gameType);
   	    return gameType;
    	//return String.valueOf(platformId);
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

    /**调用Jason Thrift接口**/
    /**
     * 获取代理参数配置信息
     * @param uid 代理id
     * @return
     */
    private AgentConfigVo getAgentConfig(long uid) {
        String body = "{\"agentId\":" + uid + "}";
        EGReq req = assembleEGReq(CmdType.CONFIG, 0x500043, body);
        try {
            EGResp resp = thriftFactory.call(req, "crius");
            if (Optional.ofNullable(resp).filter(code -> code.getCode() == 0).isPresent()){
                return JSONObject.parseObject(resp.getData(), AgentConfigVo.class);
            }
        }catch (Exception e){
            ApiLogger.error(String.format("get agent config from thrift error. uid: %d", uid), e);
        }
        return null;
    }
    /**
     * 组装thrift请求对象
     *
     * @param cmdType
     * @param cmd
     * @param body
     * @return
     */
    private EGReq assembleEGReq(CmdType cmdType, int cmd, String body) {
        EGReq req = new EGReq();
        EGHeader header = new EGHeader();
        header.setType(cmdType);
        header.setCmd(cmd);
        req.setHeader(header);
        req.setBody(body);
        return req;
    }

    private BillInfo assembleBillInfo(AgentBillReq agentBillReq){
        BillInfo billInfo = new BillInfo();
        billInfo.setOwnerId(agentBillReq.getOwnerId());
        billInfo.setProxyId(agentBillReq.getAgentId());
        billInfo.setOrderId(agentBillReq.getBillId().toString());
        billInfo.setOrderName(agentBillReq.getBillDate()+"月账单");

        //设置期数id和期数名称
        if (agentBillReq.getSchemeId() != null)
            billInfo.setPdate(Integer.parseInt(agentBillReq.getSchemeId().toString()));

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(agentBillReq.getSchemeName()))
            billInfo.setPdateName(agentBillReq.getSchemeName());

        // 代理的退佣方案12
        AgentConfigVo agentConfigVo = getAgentConfig(agentBillReq.getAgentId());
        if (agentConfigVo != null ){
            billInfo.setSchemeCode(agentConfigVo.getReturnScheme().toString());
            billInfo.setSchemeName(agentConfigVo.getReturnSchemeName());
        }

        billInfo.setAccount(agentBillReq.getCostTotalAmount());
        billInfo.setIncome(agentBillReq.getVaildBettTotalAmount());
        billInfo.setBillType(2);//1:业主;2:代理
        billInfo.setStartTime(agentBillReq.getBillStartTime());
        billInfo.setEndTime(agentBillReq.getBillEndTime());
        billInfo.setStatus(1);//1:未处理
        return billInfo;
    }

    private ProxyBillDetail assembleProxyBillDetail(AgentBillReq agentBillReq){
        ProxyBillDetail proxyBillDetail = new ProxyBillDetail();
        proxyBillDetail.setOwnerId(agentBillReq.getOwnerId());
        proxyBillDetail.setProxyId(agentBillReq.getAgentId());
        proxyBillDetail.setOrderId(agentBillReq.getBillId());

        //设置期数id
        if (agentBillReq.getSchemeId() != null)
            proxyBillDetail.setPdate(agentBillReq.getSchemeId().toString());

        //proxyBillDetail.setUserNum();;
        proxyBillDetail.setIncome(agentBillReq.getPayoffTotalAmount());
        proxyBillDetail.setReforwardAccount(agentBillReq.getRebateTotalAmount());
        proxyBillDetail.setEffectOrderCount(agentBillReq.getVaildBettTotalAmount());
        proxyBillDetail.setCost(agentBillReq.getCostTotalAmount());
        proxyBillDetail.setRecordCost(agentBillReq.getCostTotalAmount());
        proxyBillDetail.setReforwardState(1);
        proxyBillDetail.setRecordReforwardAccount(agentBillReq.getRebateTotalAmount());
        //设置有效会员数量
        UserInfo userInfo = new UserInfo();
        userInfo.setOwnerId(agentBillReq.getOwnerId());
        userInfo.setProxyId(agentBillReq.getAgentId());
        proxyBillDetail.setUserNum(Long.parseLong(userInfoService.getSummaryUserNum(userInfo)+""));
        return proxyBillDetail;
    }

    private List<ProxyBillSummary2game> assembleProxyBillSummary2Game(AgentBillReq agentBillReq){
        ProxyBillSummary2game proxyBillSummary2game = null;
        List<ProxyBillSummary2game> proxyBillSummary2gameList = new ArrayList<>();
        List<AgentHallBillVo> agentHallBillVoList = agentBillReq.getAgentHallInfos();
        String gameType = "";
        if (agentHallBillVoList != null && agentHallBillVoList.size() > 0){
            for ( AgentHallBillVo agentHallBillVo : agentHallBillVoList) {
                proxyBillSummary2game = new ProxyBillSummary2game();
                proxyBillSummary2game.setOwnerId(agentBillReq.getOwnerId());
                proxyBillSummary2game.setProxyId(agentBillReq.getAgentId());
                proxyBillSummary2game.setOrderId(agentBillReq.getBillId().toString());
                proxyBillSummary2game.setEffectOrderCount(agentHallBillVo.getVaildBettAmount());

                proxyBillSummary2game.setIncome(agentHallBillVo.getPayoffAmount());

                //设置期数id
                if (agentBillReq.getSchemeId() != null)
                    proxyBillSummary2game.setPdate(Integer.parseInt(agentBillReq.getSchemeId().toString()));

                gameType=this.getFactoryGameType(agentHallBillVo.getPlatformId(), agentHallBillVo.getHallTypeId());
                proxyBillSummary2game.setGameType(gameType);
                proxyBillSummary2game.setReforward(agentHallBillVo.getCashbackAmount());
                proxyBillSummary2game.setAdministration(agentHallBillVo.getCostAmount());
                proxyBillSummary2game.setReforwardAccount(agentHallBillVo.getRebateAmount());

                proxyBillSummary2gameList.add(proxyBillSummary2game);
            }
        }

        return proxyBillSummary2gameList;
    }


    private List<ProxyBillSummary2cost> assembleProxyBillSummary2Cost(AgentBillReq agentBillReq){
        List<ProxyBillSummary2cost> proxyBillSummary2costList = new ArrayList<>();

        ProxyBillSummary2cost proxyBillSummary2cost = new ProxyBillSummary2cost();
        proxyBillSummary2cost.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2cost.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2cost.setOrderId(agentBillReq.getBillId().toString());

        //设置期数id
        if (agentBillReq.getSchemeId() != null)
            proxyBillSummary2cost.setPdate(Integer.parseInt(agentBillReq.getSchemeId().toString()));

        proxyBillSummary2cost.setCostType("1");//手续费
        proxyBillSummary2cost.setCostTypeName("手续费");
        proxyBillSummary2cost.setCost(agentBillReq.getFeeAmount());
        proxyBillSummary2costList.add(proxyBillSummary2cost);

        ProxyBillSummary2cost proxyBillSummary2cost_1 = new ProxyBillSummary2cost();
        proxyBillSummary2cost_1.setOwnerId(agentBillReq.getOwnerId());
        proxyBillSummary2cost_1.setProxyId(agentBillReq.getAgentId());
        proxyBillSummary2cost_1.setOrderId(agentBillReq.getBillId().toString());
        //设置期数id
        if (agentBillReq.getSchemeId() != null)
            proxyBillSummary2cost_1.setPdate(Integer.parseInt(agentBillReq.getSchemeId().toString()));

        proxyBillSummary2cost_1.setCostType("2");//手续费
        proxyBillSummary2cost_1.setCostTypeName("优惠");
        proxyBillSummary2cost_1.setCost(agentBillReq.getDiscountAmount());
        proxyBillSummary2costList.add(proxyBillSummary2cost_1);

        return proxyBillSummary2costList;
    }


}
