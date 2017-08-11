package com.magic.crius.scheduled.core;

import com.magic.analysis.utils.DateKit;
import com.magic.api.commons.ApiLogger;
import com.magic.api.commons.tools.DateUtil;
import com.magic.crius.assemble.GameInfoAssemService;
import com.magic.crius.assemble.ProxyInfoAssemService;
import com.magic.crius.assemble.UserInfoAssemService;
import com.magic.crius.assemble.UserLevelAssemService;
import com.magic.crius.scheduled.consumer.*;
import com.magic.crius.service.RepairLockService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

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
    private final int cacheFlushRate = 180000;   //3分钟
    /**
     * 拉取代理列表的时间间隔
     */
    private final int proxyPullRate = 3600000;  //一小时

    /**
     * 拉取游戏列表的时间间隔
     */
    private final int gameListPullRate = 7200000; //两小时
    /**
     * 拉取游戏列表的延时时长
     */
    private final int gameListPullInitDelay = 30000;    //30秒

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
    @Resource
    private UserInfoAssemService userInfoAssemService;

    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> kafkaTemplate;

    @Resource
    private MonthJobConsumer monthJobConsumer;
    /**
     * 公司入款
     */
    @Scheduled(fixedRate = cacheFlushRate)
    public void preCmpChargeSchedule() {
        try {
            preCmpChargeReqConsumer.init(new Date());
        } catch (Exception e) {
            ApiLogger.error("proceData Egame error , ", e);
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
     * 定时获取代理列表入库
     */
    @Scheduled(fixedRate = proxyPullRate)
    public void proxyListUpdateSyncSchedule() {
        try {
            proxyInfoAssemService.batchProxyInfoSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 定时更新会员层级
     */
    @Scheduled(initialDelay = gameListPullInitDelay, fixedRate = proxyPullRate)
    public void userLevelUpdateSchedule() {
        try {
            userLevelAssemService.batchUpdateLevel(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 定时更新会员信息
     */
    @Scheduled(fixedRate = proxyPullRate)
//    @Scheduled(cron = "0 */2 * * * ?")
    public void userInfoSyncSchedule() {
        try {
        	userInfoAssemService.batchUserInfoSync();
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

    /**
     * 每天凌晨2点执行定时任务发起请求
     *
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void monthlyJobRun() {
        ApiLogger.info("monthly job run start.");
        try {
            monthJobConsumer.init();
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
