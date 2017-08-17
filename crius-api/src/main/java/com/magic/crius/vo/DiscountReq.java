package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:50
 * 优惠赠送（成功）
 */
public class DiscountReq {

    @JSONField(name = "BillId")
    private Long billId;
    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "UserId")
    private Long userId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "OfferAmount")
    private Long offerAmount;//优惠赠送额度
    @JSONField(name = "Currency")
    private String currency;//币种
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "NeedBettAmount")
    private Integer needBettAmount;//待打码量
    @JSONField(name = "OfferTypeId")
    private Integer offerTypeId; //优惠类型
    @JSONField(name = "OfferTypeName")
    private String offerTypeName; //优惠类型名称
    @JSONField(name = "ProduceTime")
    private Long produceTime;//注入kafka的ms时间

    @JSONField(name = "Balance")
    private Long balance;   //余额

    @JSONField(name = "Remark")
    private String Remark;//优惠备注

    /**
     * 消费时间
     */
    private Long consumerTime;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(Long offerAmount) {
        this.offerAmount = offerAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getNeedBettAmount() {
        return needBettAmount;
    }

    public void setNeedBettAmount(Integer needBettAmount) {
        this.needBettAmount = needBettAmount;
    }

    public Integer getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(Integer offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getOfferTypeName() {
        return offerTypeName;
    }

    public void setOfferTypeName(String offerTypeName) {
        this.offerTypeName = offerTypeName;
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

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Long getConsumerTime() {
        return consumerTime;
    }

    public void setConsumerTime(Long consumerTime) {
        this.consumerTime = consumerTime;
    }
}
