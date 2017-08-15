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

    paid(1),    //已派彩
    noPaid(2);  //未派彩

    private int value;

    private static Map<Integer, IsPaidType> maps = new HashMap<>();

    static {
        for (IsPaidType type : IsPaidType.values())
            maps.put(type.value(), type);
    }

    IsPaidType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

    public static IsPaidType parse(String des) {
        return maps.get(des);
    }


}
