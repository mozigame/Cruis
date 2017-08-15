package com.magic.crius.storage.redis;

/**
 * User: joey
 * Date: 2017/8/14
 * Time: 17:56
 */
public interface BaseReqRedisService {

    /**
     * 未处理的数据执行的页码
     * @param key
     * @return
     */
    int getNoProcPage(String key);
}
