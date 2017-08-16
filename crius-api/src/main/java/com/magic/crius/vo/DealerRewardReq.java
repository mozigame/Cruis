package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:14
 * 打赏（成功）
 */
public class DealerRewardReq {

    @JSONField(name = "ReqId")
    private Long reqId;
    @JSONField(name = "BillId")
    private Long billId; //账单编号
    @JSONField(name = "DealerId")
    private Long dealerId; //荷官编号
    @JSONField(name = "DealerName")
    private String dealerName; //荷官名字
    @JSONField(name = "UserId")
    private Long userId; //用户账户Id
    @JSONField(name = "AgentId")
    private Long agentId; //用户代理Id
    @JSONField(name = "OwnerId")
    private Long ownerId; //用户业主Id
    @JSONField(name = "RewardAmount")
    private Long rewardAmount; //打赏金额
    @JSONField(name = "CreateTime")
    private Long createTime; //打赏时间
    @JSONField(name = "GameDeskNum")
    private Long gameDeskNum; // 游戏桌号
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

    /**
     * 消费时间
     */
    private Long consumerTime;


    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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

    public Long getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Long rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getGameDeskNum() {
        return gameDeskNum;
    }

    public void setGameDeskNum(Long gameDeskNum) {
        this.gameDeskNum = gameDeskNum;
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

    public Long getConsumerTime() {
        return consumerTime;
    }

    public void setConsumerTime(Long consumerTime) {
        this.consumerTime = consumerTime;
    }

    @Override
	public String toString() {
		return "DealerRewardReq [reqId=" + reqId + ", billId=" + billId + ", dealerId=" + dealerId + ", dealerName="
				+ dealerName + ", userId=" + userId + ", agentId=" + agentId + ", ownerId=" + ownerId
				+ ", rewardAmount=" + rewardAmount + ", createTime=" + createTime + ", gameDeskNum=" + gameDeskNum
				+ ", gameId=" + gameId + ", gameName=" + gameName + ", gameHallId=" + gameHallId + ", gameHallName="
				+ gameHallName + ", gamePlatformId=" + gamePlatformId + ", gamePlatformName=" + gamePlatformName
				+ ", produceTime=" + produceTime + ", balance=" + balance + "]";
	}
    
    
}
