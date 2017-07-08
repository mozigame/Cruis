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
    @JSONField(name = "AgentHallInfos")
    private List<AgentHallBillVo> agentHallInfos;
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
}

