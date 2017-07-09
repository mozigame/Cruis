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


    @PostConstruct
    public void init() {
        THREAD_SIZE = threadSize;
        POLL_TIME = pollTime;
    }

}
