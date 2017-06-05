package org.crius.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.vo.PreCmpChargeReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 19:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-application.xml"})
public class KafkaProducerTest {


    @Resource(name = "kafkaTemplate")
    private KafkaTemplate<Integer, String> template;

    @Test
    public void testTemplate() {


        for (int i = 1; i < 8; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_CMP_CHARGE.type());
            PreCmpChargeReq req = new PreCmpChargeReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId((long) i);
            req.setAgentId((long) i);
            req.setOwnerId((long) i);
            req.setAmount(1000L);
            req.setCurrency("人民币");
            req.setRate(75);
            req.setBankCode("ICBC");
            req.setBankNum(13358820111029090L);
            req.setBankHolder("充值人");
            req.setInBankCode("CBC");
            req.setInBankNum(95588201110329090L);
            req.setInBankHolder("李磊");
            req.setInTime(System.currentTimeMillis());
            req.setProduceTime(System.currentTimeMillis());
            jsonObject.put("Data", req);
            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }
    }

    @Test
    public void testJson() {
        String str = " {\n" +
                "        \"agentId\": 2,\n" +
                "        \"amount\": 1000,\n" +
                "        \"bankCode\": \"ICBC\",\n" +
                "        \"bankHolder\": \"充值人\",\n" +
                "        \"bankNum\": 13358820111029090,\n" +
                "        \"currency\": \"人民币\",\n" +
                "        \"inBankCode\": \"CBC\",\n" +
                "        \"inBankHolder\": \"李磊\",\n" +
                "        \"inBankNum\": 95588201110329090,\n" +
                "        \"inTime\": 1496384328817,\n" +
                "        \"ownerId\": 2,\n" +
                "        \"produceTime\": 1496384328817,\n" +
                "        \"rate\": 75,\n" +
                "        \"reqId\": 2,\n" +
                "        \"userId\": 2\n" +
                "    }";
        PreCmpChargeReq req = JSON.parseObject(str, PreCmpChargeReq.class);
        System.out.println(JSONObject.toJSONString(req));
    }
}
