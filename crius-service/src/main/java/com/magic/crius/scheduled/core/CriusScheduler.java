package com.magic.crius.scheduled.core;

import com.alibaba.fastjson.JSON;
import com.magic.analysis.utils.DateKit;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.codis.JedisFactory;
import com.magic.api.commons.tools.DateUtil;
import com.magic.bc.query.service.ContractFeeService;
import com.magic.bc.query.vo.BillingCycleVo;
import com.magic.bc.query.vo.ContractFeeOwnerDetailsVo;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.assemble.*;
import com.magic.crius.constants.RedisConstants;
import com.magic.crius.po.BillInfo;
import com.magic.crius.scheduled.consumer.*;
import com.magic.crius.service.BillInfoService;
import com.magic.crius.service.MonthBillJobService;
import com.magic.crius.service.ProxyInfoService;
import com.magic.crius.service.RepairLockService;
import com.magic.crius.vo.StmlBillInfoReq;
import org.apache.commons.lang3.StringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 18:45
 * 定时筛洗数据
 */
@Service
public class CriusScheduler {

    /**
     * 处理redis数据的间隔时间
     */
    private static final int cacheFlushRate = 1000 * 60;
    /**
     * 拉取代理列表的时间间隔
     */
    private static final int proxyPullRate = 1000 * 60 * 60;

    /**
     * 拉取游戏列表的时间间隔
     */
    private static final int gameListPullRate = 1000 * 60 * 60 * 2;
    /**
     * 拉取游戏列表的延时时长
     */
    private static final int gameListPullInitDelay = 1000 * 30;

    /*用户充值成功*/
    @Resource
    private OnlChargeReqConsumer onlChargeReqConsumer;
    /*优惠赠送（成功）*/
    @Resource
    private DiscountReqConsumer discountReqConsumer;
    /*用户提现（成功）*/
    @Resource
    private PreWithdrawReqConsumer preWithdrawReqConsumer;
    /*人工充值（成功）*/
    @Resource
    private OperateChargeReqConsumer operateChargeReqConsumer;
    /*返水（成功）*/
    @Resource
    private CashbackReqConsumer cashbackReqConsumer;
    /*彩金（成功）*/
    @Resource
    private JpReqConsumer jpReqConsumer;
    /*打赏（成功）*/
    @Resource
    private DealerRewardReqConsumer dealerRewardReqConsumer;
    /*人工提现多线程处理*/
    @Resource
    private OperateWithDrawReqConsumer operateWithDrawReqConsumer;
    /*公司入款*/
    @Resource
    private PreCmpChargeReqConsumer preCmpChargeReqConsumer;

    /*代理详情*/
    @Resource
    private ProxyInfoAssemService proxyInfoAssemService;
    /*用户订单*/
    @Resource
    private BaseOrderReqConsumer baseOrderReqConsumer;
    @Resource
    private RepairLockService repairLockService;
    /*游戏列表*/
    @Resource
    private GameInfoAssemService gameInfoAssemService;
    /*会员层级*/
    @Resource
    private UserLevelAssemService userLevelAssemService;

    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Resource
    private MonthBillJobService monthBillJobService;

    @Resource
    private ProxyInfoService proxyInfoService;

    @Resource(name = "criusJedisFactory")
    private JedisFactory criusJedisFactory;

    @Resource
    private BillInfoService billInfoService;

    @Resource
    private ContractFeeService contractFeeService;
    /**
     * 公司入款
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void preCmpChargeSchedule() {
        try {
            preCmpChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户充值成功
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void onlChargeSchedule() {
        try {
            onlChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人工充值
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void operateChargeSchedule() {
        try {
            operateChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 优惠赠送
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void discountSchedule() {
        try {
            discountReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户提现
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void preWithdrawSchedule() {
        try {
            preWithdrawReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 人工提现多线程处理
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void operateWithDrawConsumerSchedule() {
        try {
            operateWithDrawReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返水
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void cashbackSchedule() {
        try {
            cashbackReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 彩金
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void jpSchedule() {
        try {
            jpReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打赏
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void dealerRewardSchedule() {
        try {
            dealerRewardReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 订单
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void baseOrderSchedule() {
        try {
            baseOrderReqConsumer.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时获取代理列表入库
     */
    @Scheduled(fixedRate = proxyPullRate)
    public void proxyListPullSchedule() {
        try {
            proxyInfoAssemService.init(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时更新会员层级
     */
    @Scheduled(fixedRate = proxyPullRate)
    public void userLevelUpdateSchedule() {
        try {
            userLevelAssemService.batchUpdateLevel(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时拉取游戏列表
     */
    @Scheduled(initialDelay = gameListPullInitDelay, fixedRate = gameListPullRate)
    public void gameInfoPullSchedule() {
        try {
            gameInfoAssemService.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时删除修复数据的锁
     * 每天的凌晨2点执行
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void repairLockSchedule() {
        try {
            repairLockService.delTimeLock(0L, System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    @Scheduled(fixedRate = 1000 * 10)
    public void sendKafkaMessage() {

        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send("cruis_capital", DateUtil.formatDateTime(new Date(), DateUtil.formatDefaultTimestamp));
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("failure");
            }

        });
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void monthlyJobRun() {
        ApiLogger.info("monthly job run start.");
        try {
            Jedis jedis = criusJedisFactory.getInstance();
            jedis.incr(RedisConstants.OWNER_BILL_KEY);
            jedis.expire(RedisConstants.OWNER_BILL_KEY,3*60*60);//3个小时存活时间
            //TODO
            if (StringUtils.isNotEmpty(jedis.get(RedisConstants.OWNER_BILL_KEY)) && Integer.parseInt(jedis.get(RedisConstants.OWNER_BILL_KEY)) == 1){
                ApiLogger.debug("begin run : ");
                //获取所有ownerId
                List<Long> ownerList = proxyInfoService.getOwenrList();

                if (ownerList != null && ownerList.size() > 0 ){
                    StmlBillInfoReq stmlBillInfoReq_owner = null;
                    String staticsDay = DateKit.isCurrentMonthMonday();
                    //TODO 当日期是当前月第一个周的周一，并且不是1号，开始统计业主账单
                    if (staticsDay.equals(DateKit.formatDate(new Date()))){
                        for (Long ownerId : ownerList){
                            stmlBillInfoReq_owner = new StmlBillInfoReq();
                            stmlBillInfoReq_owner.setOwnerId(ownerId);
                            //业主
                            stmlBillInfoReq_owner.setStartDay(Integer.parseInt(DateKit.isLastMonthMonday().replace("-","")));
                            stmlBillInfoReq_owner.setEndDay(Integer.parseInt(DateKit.lastDay().replace("-","")));
                            stmlBillInfoReq_owner.setBillType(1);//业主包网方案
                            //TODO 暂时写死 包网方案e
                            ContractFeeOwnerDetailsVo contractFeeOwnerDetailsVo = contractFeeService.getOwnerContractFeeDetails(ownerId);
                            if (contractFeeOwnerDetailsVo != null ){
                                stmlBillInfoReq_owner.setSchemeId(contractFeeOwnerDetailsVo.getId() );
                                //stmlBillInfoReq_owner.setSchemeName(stmlBillInfoReq_owner.toString().substring(0,6) + "月包网方案");
                                stmlBillInfoReq_owner.setSchemeName(contractFeeOwnerDetailsVo.getName());
                            }

                            EGResp egResp = monthBillJobService.MonthJobRun(stmlBillInfoReq_owner);
                            ApiLogger.info("ownerId " + ownerId +" 返回报文： " + egResp);
                        }
                    }


                    //代理
                    StmlBillInfoReq stmlBillInfoReq_proxy = null;
                    for (Long ownerId : ownerList){
                        stmlBillInfoReq_proxy = new StmlBillInfoReq();
                        //TODO
                        BillingCycleVo billingCycleVo = monthBillJobService.getProxyCurrentBillCycle(ownerId);
                        // 先数据库查，是否已存在改账单
                        BillInfo billInfo = new BillInfo();
                        billInfo.setStartTime(Long.parseLong(billingCycleVo.getStartTime()));
                        billInfo.setEndTime(Long.parseLong(billingCycleVo.getEndTime()));
                        billInfo.setBillType(2);//代理账单
                        billInfo.setOwnerId(ownerId);
                        billInfo.setPdate(Integer.parseInt(billingCycleVo.getStartTime().toString().substring(0,6)));
                        boolean isexist = billInfoService.isExistBill(billInfo);
                        if(!isexist){
                            stmlBillInfoReq_proxy.setStartDay(Integer.parseInt(billingCycleVo.getStartTime()));
                            stmlBillInfoReq_proxy.setEndDay(Integer.parseInt(billingCycleVo.getEndTime()));
                            stmlBillInfoReq_proxy.setBillType(2);
                            stmlBillInfoReq_proxy.setOwnerId(ownerId);
                            EGResp egResp = monthBillJobService.MonthJobRun(stmlBillInfoReq_proxy);
                            ApiLogger.info("proxy : ownerId " + ownerId +" 返回报文： " + egResp);
                        }
                    }

                }
            }
            ApiLogger.info("monthly job run end.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        Calendar firstCal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        firstCal.set(Calendar.DAY_OF_MONTH, 1);

        int i = 1;
        while(cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY && DateKit.formatDate(firstCal.getTime()) != DateKit.formatDate(new Date()) ){
            cal.set(Calendar.DAY_OF_MONTH, i++);
        }

        Date firstMonday = cal.getTime();
        System.out.println(DateKit.formatDate(firstMonday));
    }
}
