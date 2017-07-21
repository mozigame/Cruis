package com.magic.crius.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工厂类
 */
public class ThreadTaskPoolFactory {

    /**
     * 账单消费线程池
     */
    public static ExecutorService billInfoJobTaskPool = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());


}
