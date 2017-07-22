package com.magic.crius.po;

/**
 * 账户交易明细
 */
public class UserTrade {

    private Integer id;

    private Long userId;

    private Long tradeId;    //交易id

    private Long tradeNum;   //交易金额

    private Long totalNum;   //账户余额

    private Long tradeTime; //交易时间

    private Integer tradeType;  //交易类型

    private Integer actiontype; //存取类型

    private Long ownerId;

    private Integer pdate;
    
    private Integer status;

    private Long gameType;   //游戏类型

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Long getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Long tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getActiontype() {
        return actiontype;
    }

    public void setActiontype(Integer actiontype) {
        this.actiontype = actiontype;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPdate() {
        return pdate;
    }

    public void setPdate(Integer pdate) {
        this.pdate = pdate;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

    public Long getGameType() {
        return gameType;
    }

    public void setGameType(Long gameType) {
        this.gameType = gameType;
    }
}