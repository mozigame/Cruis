package com.magic.crius.storage.mongo;

import com.magic.crius.vo.JpReq;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:39
 * 彩金
 */
public interface JpReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(JpReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(JpReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    JpReq getByReqId(Long id);

    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<JpReq> reqs);

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
    List<JpReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<JpReq> getSaveFailed(Long startTime, Long endTime);

}
