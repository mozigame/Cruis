package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OperateChargeReq;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:46
 */
public interface OperateChargeReqMongoService {

    /**
     * 新增人工充值
     * @param operateChargeReq
     * @return
     */
    boolean save(OperateChargeReq operateChargeReq);

    /**
     * 保存失败的数据
     * @param operateChargeReq
     * @return
     */
    boolean saveFailedData(OperateChargeReq operateChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OperateChargeReq getByReqId(Long id);

    /**
     * 查询操作成功的ID列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<Long> getSucIds(Long startTime, Long endTime);

    /**
     * 获取固定时间内未处理的数据
     * @param startTime
     * @param endTime
     * @param reqIds
     * @return
     */
    List<OperateChargeReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OperateChargeReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<OperateChargeReq> reqs);
}
