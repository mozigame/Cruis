package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.tethys.db.UserOrderDetailMapper;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.storage.db.TethysUserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 20:38
 */
@Service
public class TethysUserOrderDetailDbServiceImpl implements TethysUserOrderDetailDbService {

    @Resource(name = "tethysUserOrderDetailMapper")
    private UserOrderDetailMapper userOrderDetailMapper;

    @Override
    public boolean batchSave(Collection<UserOrderDetail> userOrderDetails) {
        try {
            return userOrderDetailMapper.batchInsert(userOrderDetails) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
