package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:55
 * 用户提现（成功）
 */
public class PreWithdrawReq {

    private Long ReqId;
    private Long UserId;
    private Long AgentId;
    private Long OwnerId;
    private Long Amount;
    private Long UserLevel;
    private String Remark;
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

    public Long getUserLevel() {
        return UserLevel;
    }

    public void setUserLevel(Long userLevel) {
        UserLevel = userLevel;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
