package com.magic.crius.service.thrift;

import com.magic.analysis.exception.ConfigException;
import com.magic.analysis.utils.JsonUtils;
import com.magic.analysis.utils.StringUtils;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.core.exception.CommonException;
import com.magic.bc.query.service.BillingCycleService;
import com.magic.bc.query.vo.BillingCycleVo;
import com.magic.commons.enginegw.service.ThriftFactory;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.service.MonthBillJobService;
import com.magic.crius.vo.StmlBillInfoReq;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/8.
 */
@Service
public class MonthBillJobServiceImpl implements MonthBillJobService {

    @Resource
    private ThriftFactory thriftFactory;

    @Resource
    BillingCycleService billingCycleService;

    private BillingCycleVo getCurrentBillCycle(Long ownerId){
        Map<String,BillingCycleVo> BillInfo = billingCycleService.getThisBillingInfo(ownerId,System.currentTimeMillis());
        ApiLogger.info( ownerId +"的期数：" + BillInfo);
        if (BillInfo == null){
            throw new CommonException(CommonException.ERROR_LEVEL_SERVICE, 3, 0, 10, HttpServletResponse.SC_OK, "!",
                    "当前ownerId:" + ownerId + "没期数！");
        }
        BillingCycleVo billingCycleVo = (BillingCycleVo) BillInfo.get("thisBilling");
        ApiLogger.info("当前期数开始时间: " + billingCycleVo.getStartTime());
        ApiLogger.info("当前期数结束时间: " + billingCycleVo.getEndTime());
        billingCycleVo.setStartTime(billingCycleVo.getStartTime().replaceAll("-",""));
        billingCycleVo.setEndTime(billingCycleVo.getEndTime().replaceAll("-",""));
        return billingCycleVo;
    }

    @Override
    public BillingCycleVo getProxyCurrentBillCycle(Long ownerId) {
        return getCurrentBillCycle(ownerId);
    }

    @Override
    public EGResp MonthJobRun(StmlBillInfoReq stmlBillInfoReq) {

        //获取当期期数
        if (stmlBillInfoReq.getStartDay() == null || stmlBillInfoReq.getEndDay() == null){
            BillingCycleVo billingCycleVo =  getCurrentBillCycle(stmlBillInfoReq.getOwnerId());
            if (StringUtils.isStringNull(billingCycleVo.getStartTime())){
                stmlBillInfoReq.setStartDay(Integer.parseInt(billingCycleVo.getStartTime()));
            }

            if (StringUtils.isStringNull(billingCycleVo.getEndTime())){
                stmlBillInfoReq.setEndDay(Integer.parseInt(billingCycleVo.getEndTime()));
            }
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(stmlBillInfoReq.getBillDate())){
            stmlBillInfoReq.setBillDate(stmlBillInfoReq.getStartDay().toString().substring(0,6));
        }
        String body = JsonUtils.toJsonStringTrimNull(stmlBillInfoReq);
        ApiLogger.info("代理月结账单任务调度请求报文：" + body);

        //调thrift接口
        EGResp resp = thriftFactory.call(CmdType.SETTLE, 0x300013, body, "yezhu");
        if (resp == null ){
            ApiLogger.error("代理月结账单任务调度访问thrift接口失败");
            throw ConfigException.THRIFT_TIME_OUT;
        }else {
            ApiLogger.info("代理月结账单任务调度返回报文：" + resp);
            return  resp;
        }
    }

}
