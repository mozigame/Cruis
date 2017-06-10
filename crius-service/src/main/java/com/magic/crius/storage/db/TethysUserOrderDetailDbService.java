package com.magic.crius.storage.db;

import com.magic.crius.po.UserOrderDetail;

import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:38
 */
public interface TethysUserOrderDetailDbService {

    boolean batchSave(Collection<UserOrderDetail> userOrderDetails);
}
