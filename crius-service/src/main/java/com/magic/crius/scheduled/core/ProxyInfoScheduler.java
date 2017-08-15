package com.magic.crius.scheduled.core;

import com.magic.crius.assemble.ProxyInfoAssemService;
import com.magic.crius.constants.ScheduleConsumerConstants;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * User: joey
 * Date: 2017/8/11
 * Time: 20:18
 */
@Component
public class ProxyInfoScheduler {

    /*代理详情*/
    @Resource
    private ProxyInfoAssemService proxyInfoAssemService;

    /**
     * 定时获取代理列表入库
     */
    @Scheduled(fixedRate = ScheduleConsumerConstants.proxyPullRate)
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
    @Scheduled(fixedRate = ScheduleConsumerConstants.proxyPullRate)
    public void proxyListUpdateSyncSchedule() {
        try {
            proxyInfoAssemService.batchProxyInfoSync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
