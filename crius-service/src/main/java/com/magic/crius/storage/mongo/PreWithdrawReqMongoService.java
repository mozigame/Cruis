package com.magic.crius.storage.mongo;

import com.magic.crius.vo.PreWithdrawReq;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 19:14
 */
public interface PreWithdrawReqMongoService {

    /**
     * 新增用户提现
     * @param preWithdrawReq
     * @return
     */
    boolean save(PreWithdrawReq preWithdrawReq);

    /**
     * 保存失败的数据
     * @param preWithdrawReq
     * @return
     */
    boolean saveFailedData(PreWithdrawReq preWithdrawReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    PreWithdrawReq getByReqId(Long id);

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
    List<PreWithdrawReq> getNotProc(Long startTime, Long endTime, Collection<Long> reqIds, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PreWithdrawReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<PreWithdrawReq> reqs);
}
