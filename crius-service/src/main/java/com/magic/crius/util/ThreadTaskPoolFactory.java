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
    public static ExecutorService coreThreadTaskPool = new ThreadPoolExecutor(5, 15, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * kafka游戏消费线程池
     */
    public static ExecutorService kfGameThreadTaskPool = new ThreadPoolExecutor(5, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * kafka plutus消费线程池
     */
    public static ExecutorService kfPlutusThreadTaskPool = new ThreadPoolExecutor(5, 15, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());



}
