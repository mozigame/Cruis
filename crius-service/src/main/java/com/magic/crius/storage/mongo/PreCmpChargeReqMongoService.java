package com.magic.crius.storage.mongo;

import com.magic.crius.vo.PreCmpChargeReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/30
 * Time: 14:33
 */
public interface PreCmpChargeReqMongoService {

    /**
     * 新增公司入款
     * @param preCmpChargeReq
     * @return
     */
    boolean save(PreCmpChargeReq preCmpChargeReq);

    /**
     * 保存失败的数据
     * @param preCmpChargeReq
     * @return
     */
    boolean saveFailedData(PreCmpChargeReq preCmpChargeReq);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    PreCmpChargeReq getByReqId(Long id);



    /**
     * 批量存储处理成功的数据
     * @param reqs
     * @return
     */
    boolean saveSuc(Collection<PreCmpChargeReq> reqs);

    /**
     * 查询操作成功的ID列表
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取固定时间内未处理的数据
     * @return
     */
    List<PreCmpChargeReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取固定时间内mongo 插入失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PreCmpChargeReq> getSaveFailed(Long startTime, Long endTime);


}
