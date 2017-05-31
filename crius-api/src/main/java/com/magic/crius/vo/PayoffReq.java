package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:03
 * 派彩（成功）
 */
public class PayoffReq {

    private Long ReqId;
    private Long UserId;
    private Long AgentId;
    private Long OwnerId;
    private String UserName;
    private Long Amount;
    private String Currency;
    private Integer Rate;
    private Long GameHallId;
    private String GameHallName;
    private Long GamePlatformId;
    private String GamePlatformName;
    private Long ProduceTime;

    public Long getProduceTime() {
        return ProduceTime;
    }

    public void setProduceTime(Long produceTime) {
        ProduceTime = produceTime;
    }

    public Long getReqId() {
        return ReqId;
    }

    public void setReqId(Long reqId) {
        ReqId = reqId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getAgentId() {
        return AgentId;
    }

    public void setAgentId(Long agentId) {
        AgentId = agentId;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long ownerId) {
        OwnerId = ownerId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Long getAmount() {
        return Amount;
    }

    public void setAmount(Long amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public Integer getRate() {
        return Rate;
    }

    public void setRate(Integer rate) {
        Rate = rate;
    }

    public Long getGameHallId() {
        return GameHallId;
    }

    public void setGameHallId(Long gameHallId) {
        GameHallId = gameHallId;
    }

    public String getGameHallName() {
        return GameHallName;
    }

    public void setGameHallName(String gameHallName) {
        GameHallName = gameHallName;
    }

    public Long getGamePlatformId() {
        return GamePlatformId;
    }

    public void setGamePlatformId(Long gamePlatformId) {
        GamePlatformId = gamePlatformId;
    }

    public String getGamePlatformName() {
        return GamePlatformName;
    }

    public void setGamePlatformName(String gamePlatformName) {
        GamePlatformName = gamePlatformName;
    }
}
