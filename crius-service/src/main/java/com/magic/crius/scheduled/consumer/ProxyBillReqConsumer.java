package com.magic.crius.scheduled.consumer;

import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/7/13.
 */
public class ProxyBillReqConsumer implements Runnable{

    private static final Logger logger = Logger.getLogger(BaseOrderReqConsumer.class);
    private ExecutorService baseOrderTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.DiscardPolicy());
    private ExecutorService baseOrderHistoryTaskPool = Executors.newSingleThreadExecutor();

    public ProxyBillReqConsumer() {
    }

    @Override
    public void run() {

    }
}
