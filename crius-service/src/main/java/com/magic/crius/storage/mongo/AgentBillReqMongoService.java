package com.magic.crius.storage.mongo;

import java.util.Collection;
import java.util.List;

import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.ReqQueryVo;

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


}
