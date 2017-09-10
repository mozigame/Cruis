package com.magic.crius.storage.mongo;

import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.PreCmpChargeReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/2
 * Time: 0:15
 */
public interface OnlChargeReqMongoService {

    /**
     * 新增公司入款
     * @param onlChargeReq
     * @return
     */
    boolean save(OnlChargeReq onlChargeReq);

    /**
     * 保存失败的数据
     * @param onlChargeReq
     * @return
     */
    boolean saveFailedData(OnlChargeReq onlChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    OnlChargeReq getByReqId(Long id);

    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<OnlChargeReq> reqs);

    /**
     * 查询操作成功的ID列表
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取固定时间内未处理的数据
     * @return
     */
    List<OnlChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<OnlChargeReq> getSaveFailed(Long startTime, Long endTime);
}
