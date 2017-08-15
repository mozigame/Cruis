package com.magic.crius.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 17:04
 * 初始化配置参数
 */
@Component
public class ScheduleConsumerConstants {

    @Value("${pop.cache.thread.size:2}")
    private int threadSize;
    @Value("${pop.cache.thread.times:10}")
    private int pollTime;

    /*
    * 处理缓存中数据的线程数量
    * */
    public static int THREAD_SIZE;

    /**
     * 每个线程中轮询在缓存拿取数据的次数
     */
    public static int POLL_TIME;


    /**
     * 处理redis数据的间隔时间
     */
    public static final int cacheFlushRate = 180000;   //3分钟
    /**
     * 拉取代理列表的时间间隔
     */
    public static final int proxyPullRate = 3600000;  //一小时

    /**
     * 拉取游戏列表的时间间隔
     */
    public static final int gameListPullRate = 7200000; //两小时
    /**
     * 拉取游戏列表的延时时长
     */
    public static final int gameListPullInitDelay = 30000;    //30秒


    @PostConstruct
    public void init() {
        THREAD_SIZE = threadSize;
        POLL_TIME = pollTime;
    }

}
