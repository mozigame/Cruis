package com.magic.crius.po;

/**
 * 业主成本部分退佣汇总
 */
public class OwnerBillSummary2cost {
    private Integer id;

    private Long ownerId;

    private String orderId; //账单id

    private String billType;//cost类型

    private String billTypeName;    //cost类型名称

    private Long bill;//本期账单

    private Long cost;//实付金额

    private String remark;//备注

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

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType == null ? null : billType.trim();
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName == null ? null : billTypeName.trim();
    }

    public Long getBill() {
        return bill;
    }

    public void setBill(Long bill) {
        this.bill = bill;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}