package com.magic.crius.service;

import com.magic.crius.vo.EGameReq;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 17:34
 * 游戏注单
 */
public interface EGameReqService {

    /**
     * @param req
     * @return
     */
    boolean save(EGameReq req);

    /**
     * 批量添加处理成功的数据ID
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<EGameReq> reqs);


    /**
     * @param reqId
     * @return
     */
    EGameReq getByReqId(Long reqId);

    /**
     * 批量获取缓存中固定时间内的游戏注单信息
     * @param date
     * @return
     */
    List<EGameReq> batchPopRedis(Date date);
}
