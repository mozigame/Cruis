package com.magic.crius.assemble.impl;

import com.magic.crius.assemble.OwnerReforwardMoneyToGameAssemService;
import com.magic.crius.po.OwnerReforwardMoneyToGame;
import com.magic.crius.service.OwnerReforwardMoneyToGameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 19:32
 */
@Service
public class OwnerReforwardMoneyToGameAssemServiceImpl implements OwnerReforwardMoneyToGameAssemService {

    @Resource
    private OwnerReforwardMoneyToGameService ownerReforwardMoneyToGameService;

    @Override
    public boolean batchSave(List<OwnerReforwardMoneyToGame> moneyToGameMap) {

        //todo 错误处理
        if (moneyToGameMap.size() > 0) {
            ownerReforwardMoneyToGameService.batchInsert(moneyToGameMap);
        }
        return true;
    }
}
