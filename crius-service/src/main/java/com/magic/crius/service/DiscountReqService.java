package com.magic.crius.service;

import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DiscountReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:08
 * 优惠赠送
 */
public interface DiscountReqService {

    /**
     * 保存原始数据到mongo和缓存
     * @param discountReq
     * @return
     */
    boolean save(DiscountReq discountReq);

    /**
     * @return
     */
    DiscountReq getByReqId(DiscountReq req);

    /**
     * 批量获取固定时间内的数据
     * @param date
     * @return
     */
    List<DiscountReq> batchPopRedis(Date date);

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
    List<DiscountReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<DiscountReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<DiscountReq> reqs);
}
