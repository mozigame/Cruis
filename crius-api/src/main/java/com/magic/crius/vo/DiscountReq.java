package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:50
 * 优惠赠送（成功）
 */
public class DiscountReq {

    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "UserId")
    private Long userId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "Amount")
    private Long amount;//优惠赠送额度
    @JSONField(name = "Currency")
    private String currency;//币种
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "NeedBettAmount")
    private Integer needBettAmount;//待打码量
    @JSONField(name = "Status")
    private Integer status; //优惠类型
    @JSONField(name = "ProduceTime")
    private Long produceTime;//注入kafka的ms时间

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }
}
