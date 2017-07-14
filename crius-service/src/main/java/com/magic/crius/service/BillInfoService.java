package com.magic.crius.service;

import com.magic.crius.po.BillInfo;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.OwnerBillReq;

import java.util.List;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:17
 * 账单汇总表
 */
public interface BillInfoService {

    boolean save(BillInfo billInfo);
    
    public void save(OwnerBillReq req);
    
    public void save(AgentBillReq req);

    public boolean isExistBill(BillInfo billInfo);
}
