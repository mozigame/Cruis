package com.magic.crius.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * User: joey
 * Date: 2017/6/10
 * Time: 20:01
 * 用户层级
 */
public class UserLevelReq {

    @JSONField(name = "UserId")
    private Long userId;    // 用户Id
    @JSONField(name = "LevelId")
    private Integer levelId;   // 升级后的层级Id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
