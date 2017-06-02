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


        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_CMP_CHARGE.type());
            PreCmpChargeReq req = new PreCmpChargeReq();
            req.setReqId((long) i);
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
//            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(jsonObject));
        }
    }
}
