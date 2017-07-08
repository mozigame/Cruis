package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 15:36
 * 业主包网费用
 */
public class OwnerBillReq {

    @JSONField(name = "TotalCost")
    private Long totalCost;         //应付款的包网费用钱
    @JSONField(name = "RealToalCost")
    private Long realToalCost;      //实际需要的包网费用钱
    @JSONField(name = "BillStartTime")
    private Long billStartTime;
    @JSONField(name = "BillEndTime")
    private Long billEndTime;
    @JSONField(name = "OwnerCostInfo")
    private List<AmountVo> ownerCostInfo;  // 维护费、彩金、最低消费等扣除费用
    @JSONField(name = "HallBillInfos")
    private List<OwnerHallBillVo> hallBillInfos;
    @JSONField(name = "ProduceTime")
    private Long produceTime;


    // todo
    @JSONField(name = "BillId")
    private Long billId;  //账单ID
    @JSONField(name = "OwnerId")
    private Long ownerId;  //业主ID

    // 透传变量
    @JSONField(name = "AppId")
    private Long appId;
    @JSONField(name = "BillDate")
    private String billDate;  //账单日期
    @JSONField(name = "SchemeId")
    private Long schemeId;   //期数ID
    @JSONField(name = "SchemeName")
    private String schemeName; //期数名称


    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public Long getRealToalCost() {
        return realToalCost;
    }

    public void setRealToalCost(Long realToalCost) {
        this.realToalCost = realToalCost;
    }

    public Long getBillStartTime() {
        return billStartTime;
    }

    public void setBillStartTime(Long billStartTime) {
        this.billStartTime = billStartTime;
    }

    public Long getBillEndTime() {
        return billEndTime;
    }

    public void setBillEndTime(Long billEndTime) {
        this.billEndTime = billEndTime;
    }

    public List<AmountVo> getOwnerCostInfo() {
        return ownerCostInfo;
    }

    public void setOwnerCostInfo(List<AmountVo> ownerCostInfo) {
        this.ownerCostInfo = ownerCostInfo;
    }

    public List<OwnerHallBillVo> getHallBillInfos() {
        return hallBillInfos;
    }

    public void setHallBillInfos(List<OwnerHallBillVo> hallBillInfos) {
        this.hallBillInfos = hallBillInfos;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }
}
