package com.magic.crius.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.magic.crius.po.BillInfo;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.storage.db.BillInfoDbService;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.OwnerBillReq;

/**
 * User: joey
 * Date: 2017/7/8
 * Time: 14:17
 */
@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Resource
    private BillInfoDbService billInfoDbService;

    @Override
    public boolean save(BillInfo billInfo) {
        return billInfoDbService.save(billInfo);
    }
    
    public void save(OwnerBillReq req){
    	
    	//List<>  s=req.getHallBillInfos();
    	//List<>  a=req.getOwnerCostInfo();
    }
    
    public void save(AgentBillReq req){
    	
    }
    
    
}
