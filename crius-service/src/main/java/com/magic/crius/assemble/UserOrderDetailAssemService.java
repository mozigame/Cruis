package com.magic.crius.assemble;

import com.magic.crius.po.UserOrderDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:31
 * 注单详情
 */
public interface UserOrderDetailAssemService {

    boolean batchSave(List<UserOrderDetail> details);
}
