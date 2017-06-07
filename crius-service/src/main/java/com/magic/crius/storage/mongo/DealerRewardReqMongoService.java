package com.magic.crius.storage.mongo;

import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.JpReq;

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

}
