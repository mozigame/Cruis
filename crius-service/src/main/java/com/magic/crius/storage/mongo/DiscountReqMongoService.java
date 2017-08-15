package com.magic.crius.storage.mongo;

import com.magic.crius.vo.DiscountReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/3
 * Time: 10:23
 */
public interface DiscountReqMongoService {

    /**
     * 新增人工充值
     * @param discountReq
     * @return
     */
    boolean save(DiscountReq discountReq);

    /**
     * 保存失败的数据
     * @param discountReq
     * @return
     */
    boolean saveFailedData(DiscountReq discountReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    DiscountReq getByReqId(Long id);

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
    List<DiscountReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
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
