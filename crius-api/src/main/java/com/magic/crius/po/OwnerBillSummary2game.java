package com.magic.crius.po;

/**
 * 业主月游戏账单汇总
 */
public class OwnerBillSummary2game {
    private Integer id;

    private Long ownerId;

    private String orderId; //账单id

    private String pdate;   //统计期数

    private String pdateName;   //统计期数名称

    private String gameType;    //游戏类型

    private Long billCount; //应付金额

    private Long income;    //本期收益

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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate == null ? null : pdate.trim();
    }

    public String getPdateName() {
        return pdateName;
    }

    public void setPdateName(String pdateName) {
        this.pdateName = pdateName == null ? null : pdateName.trim();
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType == null ? null : gameType.trim();
    }

    public Long getBillCount() {
        return billCount;
    }

    public void setBillCount(Long billCount) {
        this.billCount = billCount;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }
}