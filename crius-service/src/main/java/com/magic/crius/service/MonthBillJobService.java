package com.magic.crius.service;


import com.magic.bc.query.vo.BillingCycleVo;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.vo.StmlBillInfoReq;

/**
 * 代理月结账单任务调度
 */
public interface MonthBillJobService {

    EGResp MonthJobRun(StmlBillInfoReq stmlBillInfoReq);

    BillingCycleVo getProxyLastBillCycle(Long ownerId);

    void RunJob();

}
