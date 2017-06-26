package org.crius.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.magic.crius.po.GameInfo;
import com.magic.crius.vo.CashbackReq;
import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 21:10
 */
public class JsonConvertTest {

    /**
     * 测试json转换花费时间
     */
    @Test
    public void testJson() {

        CashbackReq cashbackReq = new CashbackReq();
        cashbackReq.setReqId(1000L);
        cashbackReq.setUserId(1000L);
        cashbackReq.setAgentId(1000L);
        cashbackReq.setOwnerId(1000L);
        cashbackReq.setAmount(1000L);
        cashbackReq.setCurrency("人民币");
        cashbackReq.setRate(94);
        cashbackReq.setBettAmount(1000L);
        cashbackReq.setVaildBettAmount(1000L);
        cashbackReq.setGameHallId(1000L);
        cashbackReq.setGameHallName("视讯");
        cashbackReq.setGamePlatformId(1000L);
        cashbackReq.setGamePlatformName("CG");

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_0000; i ++) {
            String str = JSON.toJSONString(cashbackReq);
        }
        long medium = System.currentTimeMillis();
        System.out.println(medium - start);

        for (int i = 0; i < 100_0000; i ++) {
            String str = JSON.toJSONString(cashbackReq);
            JSON.parseObject(str, CashbackReq.class);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - medium);
    }

    @Test
    public void listJson() {
        List<String> list = new ArrayList<>();
        list.add("bbb");
        list.add("ccc");
        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void testJsonArray() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data","[{\"GameId\":1,\"Game_name\":\"百家乐\",\"Halltype_id\":1001, \"Halltype_name\":\"视讯\", \"Platform_halltype_id\":10000, \"Platform_halltype_name\":\"BB视讯\", \"Game_personal_pic\":\"\",\"Status\":1, \"Platform_id\":100, \"Platform_name\":\"MG\"},{\"GameId\":2,\"Game_name\":\"百家fds\",\"Halltype_id\":1001, \"Halltype_name\":\"视讯\", \"Platform_halltype_id\":10000, \"Platform_halltype_name\":\"BB视讯\", \"Game_personal_pic\":\"\",\"Status\":1, \"Platform_id\":100, \"Platform_name\":\"AG\"}]");
        JSONArray jsonObj = JSONArray.parseArray(jsonObject.getString("data"));
        for (Object j : jsonObj) {
            JSONObject obj1 = (JSONObject) j;
            GameInfo gameInfo = JSONObject.parseObject(obj1.toJSONString(), GameInfo.class);
            System.out.println(JSONObject.toJSONString(gameInfo));
        }
        System.out.println(jsonObj.toJSONString());
    }
}
