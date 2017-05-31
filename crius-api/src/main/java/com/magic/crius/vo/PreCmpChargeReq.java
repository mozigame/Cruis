package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:46
 * 公司入款（成功）
 */
public class PreCmpChargeReq {

    private Long ReqId;
    private Long UserId;
    private Long AgentId;
    private Long OwnerId;
    private Long Amount;
    private String Currency;
    private Integer Rate;
    private String BankCode;
    private Long BankNum;
    private String BankHolder;
    private String InBankCode;
    private Long InBankNum;
    private String InBankHolder;
    private String InBankBranch;
    private Integer BankCountry;
    private String Remark;
    private Long InTime;
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

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public Long getBankNum() {
        return BankNum;
    }

    public void setBankNum(Long bankNum) {
        BankNum = bankNum;
    }

    public String getBankHolder() {
        return BankHolder;
    }

    public void setBankHolder(String bankHolder) {
        BankHolder = bankHolder;
    }

    public String getInBankCode() {
        return InBankCode;
    }

    public void setInBankCode(String inBankCode) {
        InBankCode = inBankCode;
    }

    public Long getInBankNum() {
        return InBankNum;
    }

    public void setInBankNum(Long inBankNum) {
        InBankNum = inBankNum;
    }

    public String getInBankHolder() {
        return InBankHolder;
    }

    public void setInBankHolder(String inBankHolder) {
        InBankHolder = inBankHolder;
    }

    public String getInBankBranch() {
        return InBankBranch;
    }

    public void setInBankBranch(String inBankBranch) {
        InBankBranch = inBankBranch;
    }

    public Integer getBankCountry() {
        return BankCountry;
    }

    public void setBankCountry(Integer bankCountry) {
        BankCountry = bankCountry;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Long getInTime() {
        return InTime;
    }

    public void setInTime(Long inTime) {
        InTime = inTime;
    }
}
