package com.magic.crius.po;

/**
 * 公司入款明细
 */
public class OwnerCompanyFlowDetail {

    private Integer id;

    /**
     * 公司/业主
     */
    private Long ownerId;
    /**
     * 入款金额
     */
    private Long companyFlowMoneyCount;
    /**
     * 入款次数
     */
    private Integer companyFlowNum;
    /**
     * 账户号
     */
    private String accountCode;
    /**
     * 卡号
     */
    private String accountNum;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 银行类型
     */
    private String bankSystemCode;
    /**
     * 银行类型名称
     */
    private String bankSystemName;
    /**
     * 支付方式
     */
    private Integer payMethod;
    /**
     * 状态
     */
    private Integer state;

    private Integer pdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getCompanyFlowMoneyCount() {
        return companyFlowMoneyCount;
    }

    public void setCompanyFlowMoneyCount(Long companyFlowMoneyCount) {
        this.companyFlowMoneyCount = companyFlowMoneyCount;
    }

    public Integer getCompanyFlowNum() {
        return companyFlowNum;
    }

    public void setCompanyFlowNum(Integer companyFlowNum) {
        this.companyFlowNum = companyFlowNum;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBankSystemCode() {
        return bankSystemCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public void setBankSystemCode(String bankSystemCode) {
        this.bankSystemCode = bankSystemCode == null ? null : bankSystemCode.trim();
    }

    public String getBankSystemName() {
        return bankSystemName;
    }

    public void setBankSystemName(String bankSystemName) {
        this.bankSystemName = bankSystemName == null ? null : bankSystemName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }
}