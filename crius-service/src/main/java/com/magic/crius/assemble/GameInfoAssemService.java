package com.magic.crius.assemble;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.magic.config.thrift.base.EGResp;
import com.magic.crius.po.GameInfo;
import com.magic.crius.service.GameInfoService;
import com.magic.crius.service.thrift.CriusThirdThriftService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2017/6/26
 * Time: 12:43
 */
@Component
public class GameInfoAssemService {

    private static Logger logger = Logger.getLogger(GameInfoAssemService.class);
    @Resource
    private CriusThirdThriftService criusThirdThriftService;
    @Resource
    private GameInfoService gameInfoService;

    public void getAllGames() {
        String body = "{\"api\":\"game_list\"}";
        EGResp resp = criusThirdThriftService.getAllGames(body, "account");
        if (resp != null && resp.getCode() == 0) {
            long startTime = System.currentTimeMillis();
            JSONArray jsonObj = JSONArray.parseArray(resp.getData());
            List<GameInfo> gameInfos = new ArrayList<>();
            for (Object j : jsonObj) {
                JSONObject obj1 = (JSONObject) j;
                GameInfo gameInfo = JSONObject.parseObject(obj1.toJSONString(), GameInfo.class);
                gameInfos.add(gameInfo);
            }
            //先清空游戏表
            if (gameInfoService.deleteAll()) {
                if (!gameInfoService.batchSave(gameInfos)) {

                }
            }
            System.out.println("插入所有游戏" +(System.currentTimeMillis() - startTime));

        }
    }

}
