package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:42
 * 用户充值成功
 */
public class OnlChargeReq {

    private Long ReqId;

    private Long OrderId;

    private Long UserId;

    private Long AgentId;

    private Long OwnerId;

    private Long Amount;

    private String Currency;

    private Integer Rate;

    private Long MerchantCode;

    private String MerchantName;

    private Long PaySystemCode;

    private String PaySystemName;
    private Long ProduceTime;

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

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
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

    public Long getMerchantCode() {
        return MerchantCode;
    }

    public void setMerchantCode(Long merchantCode) {
        MerchantCode = merchantCode;
    }

    public String getMerchantName() {
        return MerchantName;
    }

    public void setMerchantName(String merchantName) {
        MerchantName = merchantName;
    }

    public Long getPaySystemCode() {
        return PaySystemCode;
    }

    public void setPaySystemCode(Long paySystemCode) {
        PaySystemCode = paySystemCode;
    }

    public String getPaySystemName() {
        return PaySystemName;
    }

    public void setPaySystemName(String paySystemName) {
        PaySystemName = paySystemName;
    }
}
