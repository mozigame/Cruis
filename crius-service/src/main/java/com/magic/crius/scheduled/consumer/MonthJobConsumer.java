package com.magic.crius.scheduled.consumer;

import com.magic.analysis.exception.ConfigException;
import com.magic.analysis.utils.DateKit;
import com.magic.analysis.utils.JsonUtils;
import com.magic.analysis.utils.StringUtils;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.core.exception.CommonException;
import com.magic.bc.query.service.BillingCycleService;
import com.magic.bc.query.service.ContractFeeService;
import com.magic.bc.query.vo.BillingCycleVo;
import com.magic.bc.query.vo.ContractFeeOwnerDetailsVo;
import com.magic.commons.enginegw.service.ThriftFactory;
import com.magic.config.thrift.base.CmdType;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.BillInfo;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.service.ProxyInfoService;
import com.magic.crius.util.ThreadTaskPoolFactory;
import com.magic.crius.vo.StmlBillInfoReq;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 代理和业主月结账单任务调度
 */
@Component
public class MonthJobConsumer {

    private ExecutorService MonthJobTaskPool = ThreadTaskPoolFactory.coreThreadTaskPool;

    @Resource
    private ThriftFactory thriftFactory;

    @Resource
    BillingCycleService billingCycleService;

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Resource
    private ProxyInfoService proxyInfoService;

    @Resource
    private BillInfoService billInfoService;

    @Resource
    private ContractFeeService contractFeeService;

    public void init() {
        doBillInfoJob();
    }

    private void doBillInfoJob() {
        MonthJobTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    RunJob();
                } catch (Exception e) {
                    ApiLogger.error("---doBillInfoJob--", e);
                }
            }
        });
    }

    private BillingCycleVo getCurrentBillCycle(Long ownerId) {
        Map<String, BillingCycleVo> BillInfo = billingCycleService.getThisBillingInfo(ownerId, System.currentTimeMillis());
        ApiLogger.info(ownerId + "的期数：" + BillInfo);
        if (BillInfo == null || BillInfo.get("thisBilling") == null) {
            return null;
        }
        BillingCycleVo billingCycleVo = (BillingCycleVo) BillInfo.get("thisBilling");
        ApiLogger.info("当前期数开始时间: " + billingCycleVo.getStartTime());
        ApiLogger.info("当前期数结束时间: " + billingCycleVo.getEndTime());
        billingCycleVo.setStartTime(billingCycleVo.getStartTime().replaceAll("-", ""));
        billingCycleVo.setEndTime(billingCycleVo.getEndTime().replaceAll("-", ""));
        return billingCycleVo;
    }

    private BillingCycleVo getLastBillCycle(Long ownerId) {
        Map<String, BillingCycleVo> BillInfo = billingCycleService.getThisBillingInfo(ownerId, System.currentTimeMillis());
        ApiLogger.info(ownerId + "的期数：" + BillInfo);
        if (BillInfo == null || BillInfo.get("lastBilling") == null) {
            return null;
        }
        BillingCycleVo billingCycleVo = (BillingCycleVo) BillInfo.get("lastBilling");
        ApiLogger.info("上期期数开始时间: " + billingCycleVo.getStartTime());
        ApiLogger.info("上期期数结束时间: " + billingCycleVo.getEndTime());
        billingCycleVo.setStartTime(billingCycleVo.getStartTime().replaceAll("-", ""));
        billingCycleVo.setEndTime(billingCycleVo.getEndTime().replaceAll("-", ""));
        return billingCycleVo;
    }

    public BillingCycleVo getProxyLastBillCycle(Long ownerId) {
        return getLastBillCycle(ownerId);
    }

    public EGResp MonthJobRun(StmlBillInfoReq stmlBillInfoReq) {

        //获取当期期数
        if (stmlBillInfoReq.getStartDay() == null || stmlBillInfoReq.getEndDay() == null) {
            BillingCycleVo billingCycleVo = getCurrentBillCycle(stmlBillInfoReq.getOwnerId());
            if (billingCycleVo != null) {
                if (StringUtils.isStringNull(billingCycleVo.getStartTime())) {
                    stmlBillInfoReq.setStartDay(Integer.parseInt(billingCycleVo.getStartTime()));
                }

                if (StringUtils.isStringNull(billingCycleVo.getEndTime())) {
                    stmlBillInfoReq.setEndDay(Integer.parseInt(billingCycleVo.getEndTime()));
                }
            }
        }
        stmlBillInfoReq.setBillDate(stmlBillInfoReq.getStartDay().toString().substring(0, 6));
        String body = JsonUtils.toJsonStringTrimNull(stmlBillInfoReq);
        ApiLogger.info("代理和业主月结账单任务调度请求报文：" + body);

        //调thrift接口
        EGResp resp = thriftFactory.call(CmdType.SETTLE, 0x300013, body, "yezhu");
        ApiLogger.info("代理和业主月结账单任务调度返回报文：" + resp);
        if (resp == null) {
            ApiLogger.error("代理和业主月结账单任务调度访问thrift接口失败");
            throw ConfigException.THRIFT_TIME_OUT;
        } else {
            return resp;
        }
    }

    /**
     * 发起请求，调thrift接口通知消息
     */
    public void RunJob() {
        Jedis jedis = criusJedisFactory.getInstance();
        jedis.incr(RedisConstants.OWNER_BILL_KEY);
        jedis.expire(RedisConstants.OWNER_BILL_KEY, 3 * 60 * 60);//3个小时存活时间

        if (org.apache.commons.lang3.StringUtils.isNotEmpty(jedis.get(RedisConstants.OWNER_BILL_KEY)) && Integer.parseInt(jedis.get(RedisConstants.OWNER_BILL_KEY)) == 1) {
            ApiLogger.debug("begin run : ");
            //获取所有ownerId
            List<Long> ownerList = proxyInfoService.getOwenrList();

            if (ownerList != null && ownerList.size() > 0) {
                StmlBillInfoReq stmlBillInfoReq_owner = null;
                String staticsDay = DateKit.isCurrentMonthMonday();
                //TODO 当日期是当前月第一个周的周一，并且不是1号，开始统计业主账单
                if (staticsDay.equals(DateKit.formatDate(new Date()))) {
                    for (Long ownerId : ownerList) {
                        stmlBillInfoReq_owner = new StmlBillInfoReq();
                        stmlBillInfoReq_owner.setOwnerId(ownerId);
                        //业主
                        stmlBillInfoReq_owner.setStartDay(Integer.parseInt(DateKit.isLastMonthMonday().replace("-", "")));
                        stmlBillInfoReq_owner.setEndDay(Integer.parseInt(DateKit.lastDay().replace("-", "")));
                        stmlBillInfoReq_owner.setBillType(1);//业主包网方案
                        //获取包网方案
                        ContractFeeOwnerDetailsVo contractFeeOwnerDetailsVo = contractFeeService.getOwnerContractFeeDetails(ownerId);
                        if (contractFeeOwnerDetailsVo != null) {
                            stmlBillInfoReq_owner.setSchemeId(contractFeeOwnerDetailsVo.getId());
                            //stmlBillInfoReq_owner.setSchemeName(stmlBillInfoReq_owner.toString().substring(0,6) + "月包网方案");
                            stmlBillInfoReq_owner.setSchemeName(contractFeeOwnerDetailsVo.getName());
                        }

                        EGResp egResp = MonthJobRun(stmlBillInfoReq_owner);
                        ApiLogger.info("ownerId " + ownerId + " 返回报文： " + egResp);
                    }
                }


                //代理
                StmlBillInfoReq stmlBillInfoReq_proxy = null;
                for (Long ownerId : ownerList) {
                    stmlBillInfoReq_proxy = new StmlBillInfoReq();
                    // 获取上一期的期数 状态 0 已停用  1 未使用  2 已使用
                    BillingCycleVo billingCycleVo = getProxyLastBillCycle(ownerId);
                   /* // 先数据库查，是否已存在改账单
                    BillInfo billInfo = new BillInfo();
                    billInfo.setStartTime(Long.parseLong(billingCycleVo.getStartTime()));
                    billInfo.setEndTime(Long.parseLong(billingCycleVo.getEndTime()));
                    billInfo.setBillType(2);//代理账单
                    billInfo.setOwnerId(ownerId);
                    billInfo.setPdate(Integer.parseInt(billingCycleVo.getStartTime().toString().substring(0,6)));
                    boolean isexist = billInfoService.isExistBill(billInfo);*/
                    //判断期数状态未使用并且结束日期等于今天，则进行结算
                    if (billingCycleVo != null) {
                        if (billingCycleVo.getStatus() == 1 && DateKit.compareDateFormat(DateKit.getCurrentDay(), billingCycleVo.getEndTime(), "YYYYMMdd") >= 0) {
                            stmlBillInfoReq_proxy.setStartDay(Integer.parseInt(billingCycleVo.getStartTime()));
                            stmlBillInfoReq_proxy.setEndDay(Integer.parseInt(billingCycleVo.getEndTime()));
                            stmlBillInfoReq_proxy.setBillType(2);//代理账单
                            stmlBillInfoReq_proxy.setOwnerId(ownerId);
                            stmlBillInfoReq_proxy.setSchemeId(billingCycleVo.getId());//期数名称
                            stmlBillInfoReq_proxy.setSchemeName(billingCycleVo.getCycleName());//期数名称
                            stmlBillInfoReq_proxy.setBillDate(billingCycleVo.getStartTime().toString().substring(0, 6));
                            EGResp egResp = MonthJobRun(stmlBillInfoReq_proxy);
                            ApiLogger.info("proxy : ownerId " + ownerId + " 返回报文： " + egResp);
                        }
                    }
                }

            }
        }
    }

    /**
     * 手动操作月结账单
     */
    public String artificialBill() {
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            jedis.set(RedisConstants.OWNER_BILL_KEY, 0 + "");
            RunJob();
        } catch (Exception e) {
            ApiLogger.error("artificialBill error:", e);
            return "false";
        }
        return "true";
    }

}
