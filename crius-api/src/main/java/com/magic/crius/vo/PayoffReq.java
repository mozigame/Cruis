package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:03
 * 派彩（成功）
 */
public class PayoffReq {

    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "UserId")
    private Long userId;
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "UserName")
    private String userName;
    @JSONField(name = "Amount")
    private Long amount;
    @JSONField(name = "Currency")
    private String currency;
    @JSONField(name = "Rate")
    private Integer rate;
    @JSONField(name = "GameHallId")
    private Long gameHallId;
    @JSONField(name = "GameHallName")
    private String gameHallName;
    @JSONField(name = "GamePlatformId")
    private Long gamePlatformId;
    @JSONField(name = "GamePlatformName")
    private String gamePlatformName;
    @JSONField(name = "ProduceTime")
    private Long produceTime;

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getGameHallId() {
        return gameHallId;
    }

    public void setGameHallId(Long gameHallId) {
        this.gameHallId = gameHallId;
    }

    public String getGameHallName() {
        return gameHallName;
    }

    public void setGameHallName(String gameHallName) {
        this.gameHallName = gameHallName;
    }

    public Long getGamePlatformId() {
        return gamePlatformId;
    }

    public void setGamePlatformId(Long gamePlatformId) {
        this.gamePlatformId = gamePlatformId;
    }

    public String getGamePlatformName() {
        return gamePlatformName;
    }

    public void setGamePlatformName(String gamePlatformName) {
        this.gamePlatformName = gamePlatformName;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }
}
