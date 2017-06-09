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
    public boolean batchSave(Map<String, OwnerReforwardMoneyToGame> moneyToGameMap) {
        Set<Long> ownerIds = new HashSet<>();
        for (OwnerReforwardMoneyToGame summmary : moneyToGameMap.values()) {
            ownerIds.add(summmary.getOwnerId());
        }
        int pdate = moneyToGameMap.values().iterator().next().getPdate();
        List<OwnerReforwardMoneyToGame> flowSummmaries = ownerReforwardMoneyToGameService.findByOwnerIds(ownerIds, pdate);
        Collection<OwnerReforwardMoneyToGame> saves = new HashSet<>();
        List<OwnerReforwardMoneyToGame> updates = new ArrayList<>();

        if (flowSummmaries != null && flowSummmaries.size() > 0) {
            Set<String> keys = moneyToGameMap.keySet();
            for (String key : keys) {
                boolean flag = false;
                for (OwnerReforwardMoneyToGame detail : flowSummmaries) {
                    if (key.equals(detail.getOwnerId() + "_" + detail.getGameType())) {
                        flowSummmaries.remove(detail);
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    updates.add(moneyToGameMap.get(key));
                } else {
                    saves.add(moneyToGameMap.get(key));
                }
            }
        } else {
            saves = moneyToGameMap.values();
        }

        boolean flag = false;
        //todo 错误处理
        if (saves.size() > 0) {
            ownerReforwardMoneyToGameService.batchInsert(saves);
        }
        for (OwnerReforwardMoneyToGame summmary : updates) {
            ownerReforwardMoneyToGameService.updateSummary(summmary);
        }
        return true;
    }
}
