package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 18:48
 * 体育注单
 */
public class SportReq extends BaseOrderReq {


    private String detail;  //内容
    private String result;  //结果

    @JSONField(name = "play_type")
    private String playType;    //盘口

    public String getPlayType() {
        return playType;
    }

    public void setPlayType(String playType) {
        this.playType = playType;
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
