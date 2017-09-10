package com.magic.crius.storage.mongo;

import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.JpReq;
import com.magic.crius.vo.ReqQueryVo;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 20:54
 * 打赏
 */
public interface DealerRewardReqMongoService {

    /**
     * @param req
     * @return
     */
    boolean save(DealerRewardReq req);

    /**
     * 保存失败的数据
     * @param req
     * @return
     */
    boolean saveFailedData(DealerRewardReq req);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    DealerRewardReq getByReqId(Long id);

    /**
     * 查询操作成功的ID列表
     * @return
     */
    List<Long> getSucIds(ReqQueryVo queryVo);

    /**
     * 获取固定时间内未处理的数据
     * @return
     */
    List<DealerRewardReq> getNotProc(ReqQueryVo queryVo, Pageable pageable);

    /**
     * 获取固定时间内处理失败的数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<DealerRewardReq> getSaveFailed(Long startTime, Long endTime);

    /**
     * 批量添加处理成功的数据id
     * @param reqs
     * @return
     */
    boolean saveSuc(List<DealerRewardReq> reqs);

}
