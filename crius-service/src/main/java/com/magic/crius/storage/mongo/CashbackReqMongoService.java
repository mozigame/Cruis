package com.magic.crius.storage.mongo;

import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DiscountReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 16:39
 * 返水
 */
public interface CashbackReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(CashbackReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(CashbackReq req);

    /**
     * 根据id获取
     * @return
     */
    CashbackReq getByReqId(CashbackReq req);

    /**
     * 保存添加成功的数据的reqId
     * @param reqIds
     * @return
     */
    boolean saveSuc(List<CashbackReq> reqIds);

    /**
     * 查询操作成功的ID列表
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取固定时间内未处理的数据
     * @return
     */
    List<CashbackReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<CashbackReq> getSaveFailed(Long startTime, Long endTime);
}
