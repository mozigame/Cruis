package com.magic.crius.po;

/**
 * 公司账目汇总
 */
public class OwnerCompanyAccountSummmary {

    private Integer id;

    /**
     * 业主id
     */
    private Long ownerId;
    /**
     * 汇总金额
     */
    private Long summaryMoneyCount;
    /**
     * 汇总用户数
     */
    private Integer summaryUserNum;
    /**
     * 汇总类型
     */
    private Integer summaryType;
    /**
     * 类型名称
     */
    private String summaryTypeName;
    /**
     *  账目类型0支出 1收入
     */
    private Integer summaryKind;
    /**
     * 统计日期
     */
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

    public Long getSummaryMoneyCount() {
        return summaryMoneyCount;
    }

    public void setSummaryMoneyCount(Long summaryMoneyCount) {
        this.summaryMoneyCount = summaryMoneyCount;
    }

    public Integer getSummaryUserNum() {
        return summaryUserNum;
    }

    public void setSummaryUserNum(Integer summaryUserNum) {
        this.summaryUserNum = summaryUserNum;
    }

    public Integer getSummaryType() {
        return summaryType;
    }

    public void setSummaryType(Integer summaryType) {
        this.summaryType = summaryType;
    }

    public String getSummaryTypeName() {
        return summaryTypeName;
    }

    public void setSummaryTypeName(String summaryTypeName) {
        this.summaryTypeName = summaryTypeName == null ? null : summaryTypeName.trim();
    }

    public Integer getSummaryKind() {
        return summaryKind;
    }

    public void setSummaryKind(Integer summaryKind) {
        this.summaryKind = summaryKind;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}