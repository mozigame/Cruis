package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OwnerBillReq;

import java.util.Collection;
import java.util.List;

/**
 * 业主包网
 * User: justin
 * Date: 2017/7/15
 */
public interface OwnerBillReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(OwnerBillReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(OwnerBillReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OwnerBillReq getByReqId(Long id);

    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<OwnerBillReq> reqs);

    /**
     * 查询操作成功的ID列表
     * @param startTime
     * @param endTime
     * @return
     */
    List<Long> getSucIds(Long startTime, Long endTime);


    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OwnerBillReq> getSaveFailed(Long startTime, Long endTime);

}
