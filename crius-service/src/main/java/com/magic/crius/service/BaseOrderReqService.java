package com.magic.crius.service;

import com.magic.crius.vo.BaseOrderReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:24
 * 视讯注单
 */
public interface BaseOrderReqService {


    /**
     * 保存原始数据到mongo和缓存
     * @param req
     * @return
     */
    boolean save(BaseOrderReq req);

    /**
     * @param req
     * @return
     */
    BaseOrderReq getByReqId(BaseOrderReq req);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<BaseOrderReq> batchPopRedis(Date date);

    /**
     * 批量添加处理成功的数据ID
     * @param reqs
     * @return
     */
    boolean saveSuc(List<BaseOrderReq> reqs);

    /**
     * 获取操作成功的ID
     * @param
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取未处理的数据
     * @return
     */
    List<BaseOrderReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime);

}
