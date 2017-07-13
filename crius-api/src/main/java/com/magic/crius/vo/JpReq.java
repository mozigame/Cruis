package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:06
 * 彩金（成功）
 */
public class JpReq {

    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "BillId")
    private Long billId; //彩金编号
    @JSONField(name = "UserId")
    private Long userId; //中奖UserId
    @JSONField(name = "AgentId")
    private Long agentId;
    @JSONField(name = "OwnerId")
    private Long ownerId;
    @JSONField(name = "JpType")
    private String jpType; //彩金类型  //todo 确定类型
    @JSONField(name = "JpAmount")
    private Long jpAmount; //彩金金额
    @JSONField(name = "CreateTime")
    private Long createTime; //中奖时间
    @JSONField(name = "GameId")
    private Long gameId; //游戏Id （百家乐）
    @JSONField(name = "GameName")
    private String gameName;
    @JSONField(name = "GameHallId")
    private Long gameHallId; //游戏厅别Id
    @JSONField(name = "GameHallName")
    private String gameHallName;
    @JSONField(name = "GamePlatformId")
    private Long gamePlatformId; //游戏厂商Id
    @JSONField(name = "GamePlatformName")
    private String gamePlatformName;
    @JSONField(name = "ProduceTime")
    private Long produceTime;

    @JSONField(name = "Balance")
    private Long balance;   //余额

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public String getJpType() {
        return jpType;
    }

    public void setJpType(String jpType) {
        this.jpType = jpType;
    }

    public Long getJpAmount() {
        return jpAmount;
    }

    public void setJpAmount(Long jpAmount) {
        this.jpAmount = jpAmount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "JpReq [reqId=" + reqId + ", billId=" + billId + ", userId=" + userId + ", agentId=" + agentId
				+ ", ownerId=" + ownerId + ", jpType=" + jpType + ", jpAmount=" + jpAmount + ", createTime="
				+ createTime + ", gameId=" + gameId + ", gameName=" + gameName + ", gameHallId=" + gameHallId
				+ ", gameHallName=" + gameHallName + ", gamePlatformId=" + gamePlatformId + ", gamePlatformName="
				+ gamePlatformName + ", produceTime=" + produceTime + ", balance=" + balance + "]";
	}
    
    
    
}
