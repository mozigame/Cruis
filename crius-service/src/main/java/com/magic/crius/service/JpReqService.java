package com.magic.crius.service;

import com.magic.crius.vo.JpReq;

import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:44
 * 彩金明细
 */
public interface JpReqService {

    /**
     * @param req
     * @return
     */
    boolean save(JpReq req);

    /**
     * @param reqId
     * @return
     */
    JpReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的彩金明细信息
     * @param date
     * @return
     */
    List<JpReq> batchPopRedis(Date date);
}
