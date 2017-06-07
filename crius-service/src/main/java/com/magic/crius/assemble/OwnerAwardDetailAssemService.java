package com.magic.crius.assemble;

import com.magic.crius.po.OwnerAwardDetail;

import java.util.List;

/**
 * User: joey
 * Date: 2017/6/6
 * Time: 14:34
 * 打赏明细
 */
public interface OwnerAwardDetailAssemService {

    boolean batchSave(List<OwnerAwardDetail> details);
}
