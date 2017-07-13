package com.magic.crius.po;

/**
 * 代理游戏部分退佣汇总
 */
public class ProxyBillSummary2game {

    private Integer id;

    private Long ownerId;

    private Long proxyId;//代理id

    private String proxyName;//代理名称

    private String orderId;//账单id

    private String gameType;//游戏类型 No

    private Long lastEffectOrderCount;//上期有效投注 no

    private Long effectOrderCount;//当期有效投注 vaildBettTotalAmount

    private Long lastIncome;//上期收益 No

    private Long income;//本期收益 payoffTotalAmount

    private Integer pdate;//统计期数 BillDate

    private Long reforward;//返水 add(AgentHallBillVo. cashbackAmount)

    private Long administration;//行政 add(AgentHallBillVo. costAmount)

    private Long reforwardAccount;//可获退佣 add(AgentHallBillVo. rebateAmount)

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public Long getLastEffectOrderCount() {
        return lastEffectOrderCount;
    }

    public void setLastEffectOrderCount(Long lastEffectOrderCount) {
        this.lastEffectOrderCount = lastEffectOrderCount;
    }

    public Long getEffectOrderCount() {
        return effectOrderCount;
    }

    public void setEffectOrderCount(Long effectOrderCount) {
        this.effectOrderCount = effectOrderCount;
    }

    public Long getLastIncome() {
        return lastIncome;
    }

    public void setLastIncome(Long lastIncome) {
        this.lastIncome = lastIncome;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }

    public Long getReforward() {
        return reforward;
    }

    public void setReforward(Long reforward) {
        this.reforward = reforward;
    }

    public Long getAdministration() {
        return administration;
    }

    public void setAdministration(Long administration) {
        this.administration = administration;
    }

    public Long getReforwardAccount() {
        return reforwardAccount;
    }

    public void setReforwardAccount(Long reforwardAccount) {
        this.reforwardAccount = reforwardAccount;
    }
}