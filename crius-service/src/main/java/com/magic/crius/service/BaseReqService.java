package com.magic.crius.service;

/**
 * User: joey
 * Date: 2017/8/15
 * Time: 10:38
 */
public interface BaseReqService {

    /**
     * 未处理的数据执行的页码
     * @param key
     * @return
     */
    int getNoProcPage(String key);

    /**
     * 获取是否执行定时任务开关
     */
    boolean getScheduleSwitch();


    /**
     * 设置是否执行定时任务开关
     */
    void setScheduleSwitch();

    /**
     * 删除是否执行定时任务的开关
     */
    void delScheduleSwitch();
}
