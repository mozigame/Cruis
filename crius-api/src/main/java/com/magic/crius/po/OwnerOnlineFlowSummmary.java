package com.magic.crius.po;

/**
 * 线上入款汇总
 */
public class OwnerOnlineFlowSummmary {

    private Integer id;

    private Long ownerId;

    private Long operateFlowMoneyCount; //入款金额

    private Integer operateFlowNum; //入款次数

    private Long merchantCode;  //商号

    private String merchantName;    //商家名称

    private Integer paySystemCode;  //支付系统码

    private String paySystemName;   //支付系统名称

    private Integer state;  //状态

    private Integer pdate;  //统计日期

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

    public Long getOperateFlowMoneyCount() {
        return operateFlowMoneyCount;
    }

    public void setOperateFlowMoneyCount(Long operateFlowMoneyCount) {
        this.operateFlowMoneyCount = operateFlowMoneyCount;
    }

    public Integer getOperateFlowNum() {
        return operateFlowNum;
    }

    public void setOperateFlowNum(Integer operateFlowNum) {
        this.operateFlowNum = operateFlowNum;
    }

    public Long getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(Long merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    public Integer getPaySystemCode() {
        return paySystemCode;
    }

    public void setPaySystemCode(Integer paySystemCode) {
        this.paySystemCode = paySystemCode;
    }

    public String getPaySystemName() {
        return paySystemName;
    }

    public void setPaySystemName(String paySystemName) {
        this.paySystemName = paySystemName == null ? null : paySystemName.trim();
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
}