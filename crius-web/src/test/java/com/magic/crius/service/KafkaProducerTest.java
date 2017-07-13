package com.magic.crius.service;

import com.alibaba.fastjson.JSON;
import com.magic.crius.vo.AgentBillReq;
import com.magic.crius.vo.PreCmpChargeReq;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

//        private Long ReqId;
//        private Long UserId;
//        private Long AgentId;
//        private Long OwnerId;
//        private Long Amount;    //公司充值金额
//        private String Currency;
//        private Integer Rate;
//        private String BankCode;    //用户银行代号
//        private Long BankNum;   //用户银行卡号
//        private String BankHolder;  //用户银行卡持有者名字
//        private String InBankCode;//转入公司账号的银行代号
//        private Long InBankNum;//转入公司账号的银行卡号
//        private String InBankHolder;//转入公司账号的银行卡持有者
//        private String InBankBranch;//转入公司账号的银行卡分支
//        private String Remark;//备注
//        private Long InTime;//转入时间
//        private Long ProduceTime;//注入kafka的ms时间

        for (int i = 0; i < 2; i++) {
            PreCmpChargeReq req = new PreCmpChargeReq();
            req.setReqId((long) i);
            req.setUserId((long) i);
            req.setAgentId((long) i);
            req.setOwnerId((long) i);
            req.setChargeAmount(1000L);
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
            template.send("test", "bza" + i);
        }
    }


    @Test
    public void TestBillInfo(){
        AgentBillReq agentBillReq = new AgentBillReq();


    }
}
