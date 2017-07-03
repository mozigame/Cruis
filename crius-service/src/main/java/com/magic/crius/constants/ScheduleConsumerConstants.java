package com.magic.crius.constants;

/**
 * User: joey
 * Date: 2017/6/9
 * Time: 17:04
 */
public class ScheduleConsumerConstants {

    /*
    * 处理缓存中数据的线程数量
    * */
    public static final int THREAD_SIZE = 2;

    /**
     * 每个线程中轮询在缓存拿取数据的次数
     */
    public static final int POLL_TIME = 10;

}
