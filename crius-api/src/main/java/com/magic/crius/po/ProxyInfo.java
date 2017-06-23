package com.magic.crius.po;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 14:16
 * 代理基础信息
 */
public class ProxyInfo {

    private Long id;

    private Long proxyId;   //代理ID

    private String proxyName;   //代理账号

    private Long shareholderId; //股东ID

    private String shareholderName;   //股东账号

    private Long ownerId;   //业主ID

    private String ownerName; //业主账号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.proxyName = proxyName;
    }

    public Long getShareholderId() {
        return shareholderId;
    }

    public void setShareholderId(Long shareholderId) {
        this.shareholderId = shareholderId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getShareholderName() {
        return shareholderName;
    }

    public void setShareholderName(String shareholderName) {
        this.shareholderName = shareholderName;
    }
}
