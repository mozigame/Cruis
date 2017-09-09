package com.magic.crius.storage.db.impl;

import com.magic.crius.dao.crius.db.UserOrderDetailExtentMapper;
import com.magic.crius.dao.crius.db.UserOrderDetailMapper;
import com.magic.crius.po.UserOrderDetail;
import com.magic.crius.storage.db.UserOrderDetailDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 16:29
 */
@Service
public class UserOrderDetailDbServiceImpl implements UserOrderDetailDbService {

    @Resource(name = "criusUserOrderDetailMapper")
    private UserOrderDetailMapper criusUserOrderDetailMapper;
    @Resource
    private UserOrderDetailExtentMapper userOrderDetailExtentMapper;

    @Override
    public boolean batchSave(List<UserOrderDetail> details) {
        if (criusUserOrderDetailMapper.batchInsert(details) > 0) {
            userOrderDetailExtentMapper.batchInsert(details);
        }
        return true;
    }

    @Override
    public boolean save(UserOrderDetail detail) {
        if (criusUserOrderDetailMapper.insert(detail) > 0) {
            userOrderDetailExtentMapper.insert(detail);
        }
        return true;
    }

    @Override
    public boolean updatePaid(UserOrderDetail detail) {
        return criusUserOrderDetailMapper.updatePaidStatus(detail);
    }

    @Override
    public List<UserOrderDetail> findByOrderId(UserOrderDetail detail) {
        return criusUserOrderDetailMapper.findByOrderId(detail);
    }

    @Override
    public List<UserOrderDetail> findByOrderIds(List<UserOrderDetail> details) {
        return criusUserOrderDetailMapper.findByOrderIds(details);
    }
}
