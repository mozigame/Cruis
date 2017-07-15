package com.magic.crius.scheduled.consumer;

import com.magic.crius.service.BillInfoService;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.PayoffReq;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * billInfo订单消息消费
 *
 */
@Component
public class BillInfoConsumer {

    private ExecutorService billInfoJobTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());

    @Resource
    private BillInfoService billInfoService;

    public void saveProxyBillInfo(AgentBillReq agentBillReq){
        doSaveProxyBillInfoJob(agentBillReq);
    }

    public void saveOwnerBillInfo(OwnerBillReq ownerBillReq){
        doSaveOwnerBillInfoJob(ownerBillReq);
    }

    /**
     * 插入代理账单数据
     * @param agentBillReq
     */
    private void doSaveProxyBillInfoJob(AgentBillReq agentBillReq) {
        billInfoJobTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                billInfoService.save(agentBillReq);
            }
        });
    }

    /**
     * 插入业主包网账单数据
     * @param ownerBillReq
     */
    private void doSaveOwnerBillInfoJob(OwnerBillReq ownerBillReq) {
        billInfoJobTaskPool.execute(new Runnable() {
            @Override
            public void run() {
                billInfoService.save(ownerBillReq);
            }
        });
    }

}
