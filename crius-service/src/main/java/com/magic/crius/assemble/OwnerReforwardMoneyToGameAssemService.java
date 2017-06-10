package com.magic.crius.assemble;

import com.magic.crius.po.OwnerReforwardMoneyToGame;

import java.util.List;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 19:31
 */
@Deprecated
public interface OwnerReforwardMoneyToGameAssemService {

    /**
     * 批量添加返水详情
     * @param userOutMoneySummaries
     */
    @Deprecated
    boolean batchSave(List<OwnerReforwardMoneyToGame> userOutMoneySummaries);
}
