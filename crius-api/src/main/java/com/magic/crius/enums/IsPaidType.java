package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/7/28
 * Time: 12:19
 * 是否派彩
 */
public enum IsPaidType {

    noPay("N", 1),
    pay("Y", 2);

    private int value;
    private String des;

    private static Map<String, IsPaidType> maps = new HashMap<>();

    static {
        for (IsPaidType type : IsPaidType.values())
            maps.put(type.getDes(), type);
    }

    IsPaidType(int value, String des) {
        this.value = value;
        this.des = des;
    }

    public int value() {
        return this.value;
    }

    public String getDes() {
        return this.des;
    }

    public static IsPaidType parse(String des) {
        return maps.get(des);
    }


}
