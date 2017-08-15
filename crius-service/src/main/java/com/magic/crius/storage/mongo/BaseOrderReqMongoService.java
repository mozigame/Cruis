package com.magic.crius.storage.mongo;

import com.magic.crius.vo.BaseOrderReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/8
 * Time: 19:10
 * 视讯注单
 */
public interface BaseOrderReqMongoService {


    /**
     * @param
     * @return
     */
    boolean save(BaseOrderReq req);

    /**
     * 保存失败的数据
     * @param
     * @return
     */
    boolean saveFailedData(BaseOrderReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    BaseOrderReq getByReqId(Long id);


    /**
     * 保存添加成功的数据的reqId
     * @param reqIds
     * @return
     */
    boolean saveSuc(Collection<BaseOrderReq> reqIds);

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
    List<BaseOrderReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<BaseOrderReq> getSaveFailed(Long startTime, Long endTime);
}
