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
    private Long Amount;    //公司充值金额
    private String Currency;
    private Integer Rate;
    private String BankCode;    //用户银行代号
    private Long BankNum;   //用户银行卡号
    private String BankHolder;  //用户银行卡持有者名字
    private String InBankCode;//转入公司账号的银行代号
    private Long InBankNum;//转入公司账号的银行卡号
    private String InBankHolder;//转入公司账号的银行卡持有者
    private String InBankBranch;//转入公司账号的银行卡分支
    private String Remark;//备注
    private Long InTime;//转入时间
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
