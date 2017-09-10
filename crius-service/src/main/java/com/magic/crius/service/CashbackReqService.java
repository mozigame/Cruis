package com.magic.crius.service;

import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:44
 * 返水
 */
public interface CashbackReqService {

    /**
     * @param req
     * @return
     */
    boolean save(CashbackReq req);

    /**
     * @return
     */
    CashbackReq getByReqId(CashbackReq req);


    /**
     * 批量获取缓存中固定时间内的返水信息
     * @param date
     * @return
     */
    List<CashbackReq> batchPopRedis(Date date);

    /**
     * 批量添加处理成功的数据ID
     * @param reqs
     * @return
     */
    boolean saveSuc(List<CashbackReq> reqs);

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
    List<CashbackReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<CashbackReq> getSaveFailed(Long startTime, Long endTime);

}
