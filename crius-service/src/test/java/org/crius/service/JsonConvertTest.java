package org.crius.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
}
