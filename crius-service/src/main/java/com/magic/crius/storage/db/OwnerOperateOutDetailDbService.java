package com.magic.crius.storage.db;

import com.magic.crius.po.OwnerOperateOutDetail;

/**
 * User: joey
 * Date: 2017/6/5
 * Time: 14:37
 * 人工出款详情
 */
public interface OwnerOperateOutDetailDbService {
    /**
     * @param detail
     * @return
     */
    boolean save(OwnerOperateOutDetail detail);

    /**
     * 判断是否存在数据
     * @param ownerId
     * @param operateOutType
     * @param pdate
     * @return
     */
    boolean checkExist(Long ownerId, Integer operateOutType, Integer pdate);

    /**
     * 修改
     * @param detail
     * @return
     */
    boolean updateSummary(OwnerOperateOutDetail detail);

}
