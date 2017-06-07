package com.magic.crius.po;

/**
 * 代理优惠汇总
 */
@Deprecated
public class ProxyPreferentialSummary {

    private Integer id;

    private Long ownerId;

    private Long proxyId;   //代理id

    private String proxyName;   //代理名称

    private Long preferentialMoneyCount;    //优惠金额

    private Integer preferentialUserNum;    //优惠会员数

    private Integer preferentialNum;    //优惠次数

    private Integer preferentialType;   //优惠类型

    private String preferentialTypeName;    //优惠类型名称

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

    public Long getPreferentialMoneyCount() {
        return preferentialMoneyCount;
    }

    public void setPreferentialMoneyCount(Long preferentialMoneyCount) {
        this.preferentialMoneyCount = preferentialMoneyCount;
    }

    public Integer getPreferentialUserNum() {
        return preferentialUserNum;
    }

    public void setPreferentialUserNum(Integer preferentialUserNum) {
        this.preferentialUserNum = preferentialUserNum;
    }

    public Integer getPreferentialNum() {
        return preferentialNum;
    }

    public void setPreferentialNum(Integer preferentialNum) {
        this.preferentialNum = preferentialNum;
    }

    public Integer getPreferentialType() {
        return preferentialType;
    }

    public void setPreferentialType(Integer preferentialType) {
        this.preferentialType = preferentialType;
    }

    public String getPreferentialTypeName() {
        return preferentialTypeName;
    }

    public void setPreferentialTypeName(String preferentialTypeName) {
        this.preferentialTypeName = preferentialTypeName == null ? null : preferentialTypeName.trim();
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }
}