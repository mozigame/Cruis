package com.magic.crius.enums;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:57
 * 数据插入失败的标识
 */
public enum FailedFlag {

    MONGO_FAILED("failed");

    private String value;

    FailedFlag(String value) {
        this.value = value;
    }

    public String failedCollName(String collection) {
        return collection+ "_" + value;
    }
}
