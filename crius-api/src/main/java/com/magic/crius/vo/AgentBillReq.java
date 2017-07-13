package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 15:30
 * 代理退佣信息
 */
public class AgentBillReq {

    @JSONField(name = "FeeAmount")
    private Long feeAmount;// 手续费
    @JSONField(name = "DiscountAmount")
    private Long discountAmount;// 优惠
    @JSONField(name = "PayoffTotalAmount")
    private Long payoffTotalAmount;// 派彩总额
    @JSONField(name = "VaildBettTotalAmount")
    private Long vaildBettTotalAmount;  //当期有效投注额  //todo
    @JSONField(name = "RebateTotalAmount")
    private Long rebateTotalAmount; //可获退佣总额  //todo
    @JSONField(name = "CostTotalAmount")
    private Long costTotalAmount;   //当期费用总额    //todo

    @JSONField(name = "AgentHallInfos")
    private List<AgentHallBillVo> agentHallInfos;
    @JSONField(name = "BillStartTime")
    private Long billStartTime;     // 账单开始时间
    @JSONField(name = "BillEndTime")
    private Long billEndTime;       // 账单结束时间
    @JSONField(name = "ProduceTime")
    private Long produceTime;

    // todo
    @JSONField(name = "BillId")
    private Long billId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "AgentId")
    private Long agentId;

    // 透传变量
    @JSONField(name = "AppId")
    private Long appId;
    @JSONField(name = "BillDate")
    private String billDate;  //账单日期
    @JSONField(name = "SchemeId")
    private Long schemeId;   //期数ID
    @JSONField(name = "SchemeName")
    private String schemeName;  //期数名称


    public Long getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Long feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getPayoffTotalAmount() {
        return payoffTotalAmount;
    }

    public void setPayoffTotalAmount(Long payoffTotalAmount) {
        this.payoffTotalAmount = payoffTotalAmount;
    }

    public List<AgentHallBillVo> getAgentHallInfos() {
        return agentHallInfos;
    }

    public void setAgentHallInfos(List<AgentHallBillVo> agentHallInfos) {
        this.agentHallInfos = agentHallInfos;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Long getBillStartTime() {
        return billStartTime;
    }

    public void setBillStartTime(Long billStartTime) {
        this.billStartTime = billStartTime;
    }

    public Long getBillEndTime() {
        return billEndTime;
    }

    public void setBillEndTime(Long billEndTime) {
        this.billEndTime = billEndTime;
    }

    public Long getVaildBettTotalAmount() {
        return vaildBettTotalAmount;
    }

    public void setVaildBettTotalAmount(Long vaildBettTotalAmount) {
        this.vaildBettTotalAmount = vaildBettTotalAmount;
    }

    public Long getRebateTotalAmount() {
        return rebateTotalAmount;
    }

    public void setRebateTotalAmount(Long rebateTotalAmount) {
        this.rebateTotalAmount = rebateTotalAmount;
    }

    public Long getCostTotalAmount() {
        return costTotalAmount;
    }

    public void setCostTotalAmount(Long costTotalAmount) {
        this.costTotalAmount = costTotalAmount;
    }

	@Override
	public String toString() {
		return "AgentBillReq [feeAmount=" + feeAmount + ", discountAmount=" + discountAmount + ", payoffTotalAmount="
				+ payoffTotalAmount + ", vaildBettTotalAmount=" + vaildBettTotalAmount + ", rebateTotalAmount="
				+ rebateTotalAmount + ", costTotalAmount=" + costTotalAmount + ", agentHallInfos=" + agentHallInfos
				+ ", billStartTime=" + billStartTime + ", billEndTime=" + billEndTime + ", produceTime=" + produceTime
				+ ", billId=" + billId + ", ownerId=" + ownerId + ", agentId=" + agentId + ", appId=" + appId
				+ ", billDate=" + billDate + ", schemeId=" + schemeId + ", schemeName=" + schemeName + "]";
	}
    
    
    
}

