package com.magic.crius.vo;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:19
 * 电子游戏
 */
public class EGameReq extends BaseOrderReq {


    private String detail;  //基注
    private String result;  //结果

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
