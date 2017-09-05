package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:19
 * 电子游戏
 */
public class EGameReq extends BaseOrderReq {


    private String detail;  //基注
    private String result;  //结果

    @JSONField(name = "game_name")
    private String gameName;    //游戏名称



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
