package com.magic.crius.enums;

import com.magic.crius.po.BillInfo;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 16:39
 * 账单类型
 */
public enum BillType {

    owner(1),
    proxy(2);

    private int type;

    BillType(int type) {
        this.type = type;
    }

    public int value() {
        return type;
    }

}

