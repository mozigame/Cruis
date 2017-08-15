package com.magic.crius.storage.mongo;

import java.util.Collection;
import java.util.List;

import com.magic.crius.vo.AgentBillReq;

/**
 * User: justin
 * Date: 2017/7/15
 */
public interface AgentBillReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(AgentBillReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(AgentBillReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    AgentBillReq getByReqId(Long id);

    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<AgentBillReq> reqs);

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
    List<AgentBillReq> getSaveFailed(Long startTime, Long endTime);

}
