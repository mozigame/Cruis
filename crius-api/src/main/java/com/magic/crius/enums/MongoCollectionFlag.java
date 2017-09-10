package com.magic.crius.enums;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:57
 * 数据插入失败的标识
 */
public enum MongoCollectionFlag {

    MONGO_FAILED("failed"),
    SAVE_SUC("suc");

    private String value;

    MongoCollectionFlag(String value) {
        this.value = value;
    }

    public String collName(String collection) {
        return collection + "_" + value;
    }

    public static String dateCollName(String collection, Integer pdate) {
        return collection + "_" + pdate;
    }
}
