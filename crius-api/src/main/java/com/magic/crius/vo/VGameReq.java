package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:05
 * 视讯注单
 */
public class VGameReq extends BaseOrderReq {



    private String detail;  //内容
    private String result;  //结果

    @JSONField(name = "game_type")
    private String gameType;    //游戏名称
    @JSONField(name = "serial_id")
    private String serialId;    //局号
    @JSONField(name = "roundno")
    private String roundno; //场次
    @JSONField(name = "table_code")
    private String tableCode;   //桌号
    @JSONField(name = "game_name")
    private String gameName;    //游戏名称

    //下注类型，无，
    //赔率拿不到，无


    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getRoundno() {
        return roundno;
    }

    public void setRoundno(String roundno) {
        this.roundno = roundno;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
