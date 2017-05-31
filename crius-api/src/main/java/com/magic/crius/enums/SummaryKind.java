package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 15:18
 */
public enum SummaryKind {

    outlay(0), //支出
    income(1);  //收入

    private static final Map<Integer, SummaryKind> maps = new HashMap<>();
    static {
        for (SummaryKind summaryKind : SummaryKind.values()) {
            maps.put(summaryKind.value, summaryKind);
        }
    }
    private int value;

    SummaryKind(int value) {
        this.value = value;
    }

    public static SummaryKind parse(int value) {
        return maps.get(value);
    }

}
