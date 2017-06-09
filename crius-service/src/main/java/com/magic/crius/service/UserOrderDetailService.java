package com.magic.crius.service;

import com.magic.crius.po.UserOrderDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:29
 * 注单详情
 */
public interface UserOrderDetailService {

    boolean batchSave(List<UserOrderDetail> details);
}
