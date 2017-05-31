package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 17:06
 * 彩金（成功）
 */
public class JpReq {

    private Long BillId; //彩金编号
    private Long UserId; //中奖UserId
    private Long AgentId;
    private Long OwnerId;
    private String JpType; //彩金类型  //todo 确定类型
    private Long JpAmount; //彩金金额
    private Long CreateTime; //中奖时间
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

    public String getJpType() {
        return JpType;
    }

    public void setJpType(String jpType) {
        JpType = jpType;
    }

    public Long getJpAmount() {
        return JpAmount;
    }

    public void setJpAmount(Long jpAmount) {
        JpAmount = jpAmount;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
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
