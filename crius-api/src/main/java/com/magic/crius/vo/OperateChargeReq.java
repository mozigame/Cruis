package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:56
 * 人工充值（成功）
 */
public class OperateChargeReq {

    private Long ReqId;
    private Long UserIds[];
    private Long OwnerId;
    private Long AgentId;
    private Long Amount;
    private String Currency;
    private Integer Rate;
    // 存款优惠
    private Long DepositOffer;
    // 汇款优惠
    private Long RemittanceOffer;
    private Long HandlerId;
    private String HandlerName;
    private Integer Type;
    private String Remark;
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

    public Long[] getUserIds() {
        return UserIds;
    }

    public void setUserIds(Long[] userIds) {
        UserIds = userIds;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long ownerId) {
        OwnerId = ownerId;
    }

    public Long getAgentId() {
        return AgentId;
    }

    public void setAgentId(Long agentId) {
        AgentId = agentId;
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

    public Long getDepositOffer() {
        return DepositOffer;
    }

    public void setDepositOffer(Long depositOffer) {
        DepositOffer = depositOffer;
    }

    public Long getRemittanceOffer() {
        return RemittanceOffer;
    }

    public void setRemittanceOffer(Long remittanceOffer) {
        RemittanceOffer = remittanceOffer;
    }

    public Long getHandlerId() {
        return HandlerId;
    }

    public void setHandlerId(Long handlerId) {
        HandlerId = handlerId;
    }

    public String getHandlerName() {
        return HandlerName;
    }

    public void setHandlerName(String handlerName) {
        HandlerName = handlerName;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
