package com.magic.crius.service;

import com.magic.crius.vo.DealerRewardReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 22:34
 * 打赏
 */
public interface DealerRewardReqService {

    /**
     * @param req
     * @return
     */
    boolean save(DealerRewardReq req);

    /**
     * @param reqId
     * @return
     */
    DealerRewardReq getByReqId(Long reqId);

    /**
     * 批量获取固定时间内的打赏信息
     * @param date
     * @return
     */
    List<DealerRewardReq> batchPopRedis(Date date);

    /**
     * 获取操作成功的ID
     * @param
     * @return
     */
    List<Long> getSucIds(Long startTime, Long endTime);

    /**
     * 获取未处理的数据
     * @param startTime
     * @param endTime
     * @param reqIds
     * @return
     */
    List<DealerRewardReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取一段时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealerRewardReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<DealerRewardReq> reqs);
}
