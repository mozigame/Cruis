package com.magic.crius.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:25
 * 游戏注单详情
 */
public class BaseOrderReq {

    @JSONField(name = "req_id")
    private Long reqId;
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "owner_id")
    private Long ownerId;
    @JSONField(name = "bc_bet_id")
    private Long bcBetId;   //注单号，改后
    @JSONField(name = "bet_id")
    private String betId; //主单号，原始游戏单号
    @JSONField(name = "game_id")
    private Long gameId;    //
    @JSONField(name = "game_name")
    private String gameName;    //游戏名称
    @JSONField(name = "insert_datetime")
    private Long insertDatetime; //插入日期
    @JSONField(name = "update_datetime")
    private Long updateDatetime;    //修改日期
    @JSONField(name = "bet_datetime")
    private Long betDatetime;   //下注日期
    @JSONField(name = "bet_amount")
    private Long betAmount; //投注金额
    @JSONField(name = "valid_bet_amount")
    private Long validBetAmount;    //有效投注金额
    private Long payoff;    //派彩金额
    private Long produceTime;  //
    @JSONField(name = "flag")
    private Integer isPaid; //是否已派彩
    //扩展消息
    private JSONObject orderExtent;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getBcBetId() {
        return bcBetId;
    }

    public void setBcBetId(Long bcBetId) {
        this.bcBetId = bcBetId;
    }

    public String getBetId() {
        return betId;
    }

    public void setBetId(String betId) {
        this.betId = betId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getInsertDatetime() {
        return insertDatetime;
    }

    public void setInsertDatetime(Long insertDatetime) {
        this.insertDatetime = insertDatetime;
    }

    public Long getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Long updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Long getBetDatetime() {
        return betDatetime;
    }

    public void setBetDatetime(Long betDatetime) {
        this.betDatetime = betDatetime;
    }

    public Long getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Long betAmount) {
        this.betAmount = betAmount;
    }

    public Long getValidBetAmount() {
        return validBetAmount;
    }

    public void setValidBetAmount(Long validBetAmount) {
        this.validBetAmount = validBetAmount;
    }

    public Long getPayoff() {
        return payoff;
    }

    public void setPayoff(Long payoff) {
        this.payoff = payoff;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }

    public Long getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Long produceTime) {
        this.produceTime = produceTime;
    }

    public JSONObject getOrderExtent() {
        return orderExtent;
    }

    public void setOrderExtent(JSONObject orderExtent) {
        this.orderExtent = orderExtent;
    }

    public Integer getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Integer isPaid) {
        this.isPaid = isPaid;
    }
}
