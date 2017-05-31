package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:14
 *打赏（成功）
 */
public class DealerRewardReq {

    private Long BillId; //账单编号
    private Long DealerId; //荷官编号
    private String DealerName; //荷官名字
    private Long UserId; //用户账户Id
    private Long AgentId; //用户代理Id
    private Long OwnerId; //用户业主Id
    private Long RewardAmount; //打赏金额
    private Long CreateTime; //打赏时间
    private Long GameDeskNum; // 游戏桌号
    private Long GameId; //游戏Id （百家乐）
    private String GameName;
    private Long GameHallId; //游戏厅别Id
    private String GameHallName;
    private Long GamePlatformId; //游戏厂商Id
    private String GamePlatformName;
    private Long ProduceTime;

    public Long getProduceTime() {
        return ProduceTime;
    }

    public void setProduceTime(Long produceTime) {
        ProduceTime = produceTime;
    }

    public Long getBillId() {
        return BillId;
    }

    public void setBillId(Long billId) {
        BillId = billId;
    }

    public Long getDealerId() {
        return DealerId;
    }

    public void setDealerId(Long dealerId) {
        DealerId = dealerId;
    }

    public String getDealerName() {
        return DealerName;
    }

    public void setDealerName(String dealerName) {
        DealerName = dealerName;
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

    public Long getRewardAmount() {
        return RewardAmount;
    }

    public void setRewardAmount(Long rewardAmount) {
        RewardAmount = rewardAmount;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public Long getGameDeskNum() {
        return GameDeskNum;
    }

    public void setGameDeskNum(Long gameDeskNum) {
        GameDeskNum = gameDeskNum;
    }

    public Long getGameId() {
        return GameId;
    }

    public void setGameId(Long gameId) {
        GameId = gameId;
    }

    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
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
