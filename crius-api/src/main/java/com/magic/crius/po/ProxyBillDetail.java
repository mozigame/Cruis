package com.magic.crius.po;

/**
 * 代理账单汇总
 */
public class ProxyBillDetail {
    private Integer id;

    private Long ownerId;

    private Long proxyId;   //代理id

    private String proxyName;   //代理名称

    private Long orderId; //账单编号 billId

    private String pdate;//统计期数 BillDate

    private Long userNum;//会员数量  no

    private Long income;//本期收益 payoffTotalAmount

    private Long reforwardAccount;//可获退佣 rebateTotalAmount

    private Long effectOrderCount;//当期有效投注 vaildBettTotalAmount

    private Long cost;//当期累计费用 costTotalAmount

    private Long recordReforwardAccount;//已获退佣 rebateTotalAmount

    private Integer reforwardState;//退佣状态;默认1是未处理

        private Long recordCost;//当期费用扣除
    private Long effectIncome;//本期有效收益
    private Long orderCount;//当期总投注

    public Long getRecordCost() {
        return recordCost;
    }

    public void setRecordCost(Long recordCost) {
        this.recordCost = recordCost;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getEffectIncome() {
        return effectIncome;
    }

    public void setEffectIncome(Long effectIncome) {
        this.effectIncome = effectIncome;
    }

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

    public Long getProxyId() {
        return proxyId;
    }

    public void setProxyId(Long proxyId) {
        this.proxyId = proxyId;
    }

    public String getProxyName() {
        return proxyName;
    }

    public void setProxyName(String proxyName) {
        this.proxyName = proxyName == null ? null : proxyName.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate == null ? null : pdate.trim();
    }

    public Long getUserNum() {
        return userNum;
    }

    public void setUserNum(Long userNum) {
        this.userNum = userNum;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getEffectOrderCount() {
        return effectOrderCount;
    }

    public void setEffectOrderCount(Long effectOrderCount) {
        this.effectOrderCount = effectOrderCount;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getReforwardAccount() {
        return reforwardAccount;
    }

    public void setReforwardAccount(Long reforwardAccount) {
        this.reforwardAccount = reforwardAccount;
    }

    public Long getRecordReforwardAccount() {
        return recordReforwardAccount;
    }

    public void setRecordReforwardAccount(Long recordReforwardAccount) {
        this.recordReforwardAccount = recordReforwardAccount;
    }

    public Integer getReforwardState() {
        return reforwardState;
    }

    public void setReforwardState(Integer reforwardState) {
        this.reforwardState = reforwardState;
    }
}