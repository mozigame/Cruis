package com.magic.crius.vo;

/**
 * 代理月结账单通知请求报文
 */
public class StmlBillInfoReq {

    private Integer StartDay;

    private Integer EndDay;

    private Long OwnerId;

    private Long AppId;

    private String BillDate;

    private Long SchemeId; //期数ID

    private String SchemeName;//期数名称

    private Integer BillType ;// 账单类型： 1 业主包网费，2 代理退佣费

    public Integer getBillType() {
        return BillType;
    }

    public void setBillType(Integer billType) {
        BillType = billType;
    }

    public Integer getStartDay() {
        return StartDay;
    }

    public void setStartDay(Integer startDay) {
        StartDay = startDay;
    }

    public Integer getEndDay() {
        return EndDay;
    }

    public void setEndDay(Integer endDay) {
        EndDay = endDay;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long ownerId) {
        OwnerId = ownerId;
    }

    public Long getAppId() {
        return AppId;
    }

    public void setAppId(Long appId) {
        AppId = appId;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public Long getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(Long schemeId) {
        SchemeId = schemeId;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }
}
