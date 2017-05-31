package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:50
 * 优惠赠送（成功）
 */
public class DiscountReq {

    private Long ReqId;
    private Long UserId;
    private Long AgentId;
    private Long OwnerId;
    private Long Amount;//优惠赠送额度
    private String Currency;//币种
    private Integer Rate;
    private Integer NeedBettAmount;//待打码量
    private Integer Status; //优惠类型
    private Long ProduceTime;//注入kafka的ms时间

    public Long getProduceTime() {
        return ProduceTime;
    }

    public void setProduceTime(Long produceTime) {
        ProduceTime = produceTime;
    }
    public Long getReqId() {
        return ReqId;
    }

    public void setReqId(Long reqId) {
        ReqId = reqId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getAgentId() {
        return AgentId;
    }

    public void setAgentId(Long agentId) {
        AgentId = agentId;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long ownerId) {
        OwnerId = ownerId;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public Integer getRate() {
        return Rate;
    }

    public void setRate(Integer rate) {
        Rate = rate;
    }

    public Integer getNeedBettAmount() {
        return NeedBettAmount;
    }

    public void setNeedBettAmount(Integer needBettAmount) {
        NeedBettAmount = needBettAmount;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }
}
