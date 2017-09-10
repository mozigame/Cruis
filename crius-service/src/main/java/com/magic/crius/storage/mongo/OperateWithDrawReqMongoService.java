package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 15:38
 * 人工提现
 */
public interface OperateWithDrawReqMongoService {
    /**
     * @param req
     * @return
     */
    boolean save(OperateWithDrawReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(OperateWithDrawReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OperateWithDrawReq getByReqId(Long id);

    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<OperateWithDrawReq> reqs);

    /**
     * 查询操作成功的ID列表
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取固定时间内未处理的数据
     * @return
     */
    List<OperateWithDrawReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OperateWithDrawReq> getSaveFailed(Long startTime, Long endTime);
}
