package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 18:02
 * 彩票注单
 */
public class LotteryReq extends BaseOrderReq {


    private String detail;  //内容
    private String result;  //结果

    @JSONField(name = "lottery_type")
    private String lotteryType; //彩种
    @JSONField(name = "play_type")
    private String playType;    //玩法
    @JSONField(name = "game_code")
    private String gameCode;

    public String getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(String lotteryType) {
        this.lotteryType = lotteryType;
    }

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
