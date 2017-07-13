package org.crius.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.magic.analysis.utils.DateKit;
import com.magic.crius.enums.KafkaConf;
import com.magic.crius.vo.AmountVo;
import com.magic.crius.vo.CashbackReq;
import com.magic.crius.vo.DealerRewardReq;
import com.magic.crius.vo.DiscountReq;
import com.magic.crius.vo.EGameReq;
import com.magic.crius.vo.JpReq;
import com.magic.crius.vo.LotteryReq;
import com.magic.crius.vo.OnlChargeReq;
import com.magic.crius.vo.OperateChargeReq;
import com.magic.crius.vo.OperateWithDrawReq;
import com.magic.crius.vo.OwnerBillReq;
import com.magic.crius.vo.OwnerHallBillVo;
import com.magic.crius.vo.PreCmpChargeReq;
import com.magic.crius.vo.PreWithdrawReq;
import com.magic.crius.vo.SportReq;
import com.magic.crius.vo.VGameReq;

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

    private static final String TOPIC = "plutus";

    private static final String DATA = "Data";
    private static final String DATA_TYPE = "DataType";

    @Test
    public void testCashback() {
        for (int i = 0; i < 40; i++) {
            CashbackReq req = new CashbackReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(2000001L + i);
            req.setAgentId(105094L);
            req.setOwnerId(10001L);
            req.setAmount((long) new Random().nextInt(100));
            req.setCurrency("人民币");
            req.setRate(95);
            req.setBettAmount((long) new Random().nextInt(562));
            req.setVaildBettAmount((long) new Random().nextInt(200));
            req.setGameHallId(1000L);
            req.setGameHallName("视讯" + i);
            req.setGamePlatformId(100004L);
            req.setGamePlatformName("BBIN" + i);
            req.setProduceTime(System.currentTimeMillis());


            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_CAHSBACK.type());
            jsonObject.put(DATA, req);
            template.send(TOPIC, jsonObject.toJSONString());

        }
    }

    @Test
    public void testDealerReward() {
        for (int i = 0; i < 40; i++) {
            DealerRewardReq dealer = new DealerRewardReq();
            dealer.setReqId(System.currentTimeMillis() + i);
            dealer.setBillId(System.currentTimeMillis() + i);
            dealer.setDealerId(1000L);
            dealer.setDealerName("荷官" + i);
            dealer.setUserId(2000001L + i);
            dealer.setAgentId(105094L);
            dealer.setOwnerId(10001L);
            dealer.setRewardAmount((long) (new Random().nextInt(599)));
            dealer.setCreateTime(System.currentTimeMillis());
            dealer.setGameDeskNum(1000L);
            dealer.setGameId(1000L);
            dealer.setGameName("捕鱼达人");
            dealer.setGameHallId(1000L);
            dealer.setGameHallName("视讯");
            dealer.setGamePlatformId(1000L);
            dealer.setGamePlatformName("BBIN");
            dealer.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_DS.type());
            jsonObject.put(DATA, dealer);
            template.send(TOPIC, jsonObject.toJSONString());

        }
    }

    @Test
    public void testDiscount() {
        for (int i = 0; i < 1; i++) {
            DiscountReq discount = new DiscountReq();
            discount.setBillId(System.currentTimeMillis());
            discount.setOwnerId(10001L);
            discount.setReqId(System.currentTimeMillis() + i);
            discount.setUserId(2000001L + i);
            discount.setAgentId(105094L);
            discount.setOfferAmount((long) (new Random().nextInt(500)));
            discount.setCurrency("人民币");
            discount.setRate(87);
            discount.setNeedBettAmount(new Random().nextInt(300));
            discount.setOfferTypeId(200);
            discount.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_DISCOUNT.type());
            jsonObject.put(DATA, discount);
            template.send(TOPIC, jsonObject.toJSONString());

        }
    }
    
    @Test
    public void testBillOwner() {
        for (int i = 0; i < 2; i++) {
        	OwnerBillReq bill = new OwnerBillReq();
        	bill.setAppId(System.currentTimeMillis() + i);
        	bill.setBillId(System.currentTimeMillis() + i);
        	bill.setBillDate(""+(new Date().getTime() + i));
        	
        	bill.setBillEndTime(new Date().getTime());
        	bill.setBillStartTime(new Date().getTime());
        	bill.setOwnerId(10001L);
        	
        	List<AmountVo> list=new ArrayList();
        	AmountVo amount=null;
        	for(int j=0; j<2; j++){
        		amount=new AmountVo();
        		amount.setAmountTypeId(10l);
        		amount.setAmountTypeName("10test");
        		amount.setAmount(10l+j);
        		list.add(amount);
        	}
        	bill.setOwnerCostInfo(list);
        	
        	List<OwnerHallBillVo> billList=new ArrayList();
        	OwnerHallBillVo billInfo=null;
        	for(int j=0; j<3; j++){
        		billInfo=new OwnerHallBillVo();
        		billInfo.setHallTypeId(1000l);
        		billInfo.setPlatformId(1001l);
        		billInfo.setPayOffAmount(100l);
        		billInfo.setNeedPayAmount(105l);
        		billList.add(billInfo);
        	}
        	bill.setHallBillInfos(billList);
            bill.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_OWNER_BILL.type());
            jsonObject.put(DATA, bill);
            template.send(TOPIC, jsonObject.toJSONString());
        }
    }

    @Test
    public void testJp() {
        for (int i = 0; i < 2; i++) {
            JpReq jp = new JpReq();
            jp.setReqId(System.currentTimeMillis() + i);
            jp.setBillId(System.currentTimeMillis() + i);
            jp.setUserId(2000001L + i);
            jp.setAgentId(105094L);
            jp.setOwnerId(10001L);
            jp.setJpType("300");
            jp.setJpAmount((long) (new Random().nextInt(4000)));
            jp.setCreateTime(System.currentTimeMillis());
            jp.setGameId(1000L);
            jp.setGameName("捕鱼达人");
            jp.setGameHallId(1000L);
            jp.setGameHallName("视讯");
            jp.setGamePlatformId(1000L);
            jp.setGamePlatformName("BBIN");
            jp.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_JP.type());
            jsonObject.put(DATA, jp);
            template.send(TOPIC, jsonObject.toJSONString());
        }
    }

    @Test
    public void testOnlCharge() {
        for (int i = 0; i < 10; i++) {
            OnlChargeReq onl = new OnlChargeReq();
            onl.setReqId(System.currentTimeMillis() + i);
            onl.setOrderId(System.currentTimeMillis() + i);
            onl.setBillId(System.currentTimeMillis() + i);
            onl.setUserId(455L + i);
            onl.setAgentId(105094L);
            onl.setOwnerId(10001L);
            onl.setChargeAmount((long) (new Random().nextInt(5000)));
            onl.setCurrency("人民币");
            onl.setRate(45);
            onl.setMerchantCode(1000L);
            onl.setMerchantName("银联");
            onl.setPaySystemCode(50000);
            onl.setPaySystemName("小军");
            onl.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_ONL_CHARGE.type());
            jsonObject.put(DATA, onl);
            template.send(TOPIC, jsonObject.toJSONString());
        }


        for (int i = 10; i < 20; i++) {
            OnlChargeReq onl = new OnlChargeReq();
            onl.setReqId(System.currentTimeMillis() + i);
            onl.setOrderId(System.currentTimeMillis() + i);
            onl.setBillId(System.currentTimeMillis() + i);
            onl.setUserId(567L + i);
            onl.setAgentId(105094L);
            onl.setOwnerId(10001L);
            onl.setChargeAmount((long) (new Random().nextInt(5000)));
            onl.setCurrency("人民币");
            onl.setRate(45);
            onl.setMerchantCode(1000L);
            onl.setMerchantName("银联");
            onl.setPaySystemCode(50000);
            onl.setPaySystemName("小军");
            onl.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_ONL_CHARGE.type());
            jsonObject.put(DATA, onl);
            template.send(TOPIC, jsonObject.toJSONString());
        }


//        for (int i = 0; i < 10; i++) {
//            OnlChargeReq onl = new OnlChargeReq();
//            onl.setReqId(System.currentTimeMillis() + i);
//            onl.setOrderId(System.currentTimeMillis() + i);
//            onl.setBillId(System.currentTimeMillis() + i);
//            onl.setUserId(988L + i);
//            onl.setAgentId(105094L);
//            onl.setOwnerId(10001L);
//            onl.setAmount((long) (new Random().nextInt(5000)));
//            onl.setCurrency("人民币");
//            onl.setRate(45);
//            onl.setMerchantCode(1000L);
//            onl.setMerchantName("银联");
//            onl.setPaySystemCode(50000);
//            onl.setPaySystemName("小军");
//            onl.setProduceTime(System.currentTimeMillis());
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_ONL_CHARGE.type());
//            jsonObject.put(DATA, onl);
//            template.send(TOPIC, jsonObject.toJSONString());
//        }


    }

    @Test
    public void testOperateCharge() {
        for (int i = 0; i < 2; i++) {
            long currentTime = System.currentTimeMillis();
            OperateChargeReq operateCharge = new OperateChargeReq();
            operateCharge.setReqId(currentTime + i);
            operateCharge.setUserIds(new Long[]{2000001L + 1, 2000001L + 2, 2000001L + 3});
            operateCharge.setAgentId(105094L);
            operateCharge.setOwnerId(10001L);
            operateCharge.setChargeAmount((long) (new Random().nextInt(99999)));
            operateCharge.setRate(45);
            operateCharge.setDepositOffer(100L);
            operateCharge.setRemittanceOffer(1000L);
            operateCharge.setHandlerId(20000L);
            operateCharge.setHandlerName("junit");
            operateCharge.setType(200);
            operateCharge.setRemark("remark");
            operateCharge.setProduceTime(currentTime);


            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_OPR_CHARGE.type());
            jsonObject.put(DATA, operateCharge);
            template.send(TOPIC, jsonObject.toJSONString());

        }
    }

    @Test
    public void testOperateWithDraw() {
        for (int i = 0; i < 20; i++) {
            long currentTime = System.currentTimeMillis();
            OperateWithDrawReq operateWithDraw = new OperateWithDrawReq();
            operateWithDraw.setReqId(currentTime + i);
            operateWithDraw.setOwnerId(10001L);
            operateWithDraw.setUserIds(new Long[]{2000001L + 1, 2000001L + 2, 2000001L + 3});
            operateWithDraw.setAmount(1L);
            operateWithDraw.setCurrency("人民币");
            operateWithDraw.setRate(45);
            operateWithDraw.setHandlerId(20000L);
            operateWithDraw.setHandlerName("jee");
            operateWithDraw.setWithdrawType(i % 5);
            operateWithDraw.setRemark("remark");
            operateWithDraw.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(DATA_TYPE, KafkaConf.DataType.PLUTUS_OPR_WITHDRAW.type());
            jsonObject.put(DATA, operateWithDraw);
            template.send(TOPIC, jsonObject.toJSONString());
        }
    }

    @Test
    public void testPreWithdraw() {
        for (int i = 0; i <= 2; i++) {
            PreWithdrawReq preWithdraw = new PreWithdrawReq();
            preWithdraw.setReqId(System.currentTimeMillis() + i);
            preWithdraw.setUserId(105094L);
            preWithdraw.setAgentId(105094L);
            preWithdraw.setOwnerId(10001L);
            preWithdraw.setReqWithdrawAmount((long) (new Random().nextInt(99999)));
            preWithdraw.setUserLevel(100L);
            preWithdraw.setRemark("remark");
            preWithdraw.setProduceTime(System.currentTimeMillis());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_USER_WITHDRAW.type());
            jsonObject.put(DATA, preWithdraw);
            template.send(TOPIC, jsonObject.toJSONString());

        }
    }

    @Test
    public void testPreCmpCharge() {
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_CMP_CHARGE.type());
            PreCmpChargeReq req = new PreCmpChargeReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(105094L);
            req.setAgentId(105094L);
            req.setOwnerId(10001L);
            req.setChargeAmount(1000L);
            req.setCurrency("人民币");
            req.setRate(75);
            req.setBankCode("ICBC");
            req.setBankNum(13358820111029090L);
            req.setBankHolder("莉莉");
            req.setInBankCode("CBC");
            req.setInBankNum(95588201110329090L);
            req.setInBankHolder("李磊");
            req.setInTime(System.currentTimeMillis());
            req.setProduceTime(System.currentTimeMillis());
            jsonObject.put(DATA, req);
            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }
    }

    @Test
    public void testSportReq() {
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_SPORT.type());
            SportReq req = new SportReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(2000001L + i);
            req.setOwnerId(10001L);
            req.setBetId(System.currentTimeMillis() + i);
            req.setBcBetId(System.currentTimeMillis() + i * 100);
            req.setGameId(100L +i);
            req.setInsertDatetime(System.currentTimeMillis());
            req.setUpdateDatetime(System.currentTimeMillis());
            req.setBetDatetime(System.currentTimeMillis());
            req.setBetAmount(100L);
            req.setValidBetAmount(1000L);
            req.setPayoff(10L);
            req.setGameName("足球");

            req.setDetail("足球-单式标准盘\n" +
                    "中国家族联赛 2017-05-06\n" +
                    "武汉卓尔 VS 石家庄永昌\n" +
                    "武汉 卓尔 @ 1.74");
            req.setPlayType("香港盘");
            req.setResult("输");

            jsonObject.put(DATA, req);
            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }
    }

    @Test
    public void testVGameReq() {
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_SPORT.type());
            VGameReq req = new VGameReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(2000001L + i);
            req.setOwnerId(10001L);
            req.setBetId(System.currentTimeMillis() + i);
            req.setBcBetId(System.currentTimeMillis() + i * 100);
            req.setGameId(100L +i);
            req.setInsertDatetime(System.currentTimeMillis());
            req.setUpdateDatetime(System.currentTimeMillis());
            req.setBetDatetime(System.currentTimeMillis());
            req.setBetAmount(100L);
            req.setValidBetAmount(1000L);
            req.setPayoff(10L);

            req.setGameType("百家乐");
            req.setSerialId("44903");
            req.setRoundno("C-919");
            req.setTableCode("C");
            req.setDetail("庄（9）闲（8）");

            jsonObject.put(DATA, req);
            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }
    }

    @Test
    public void testLotteryReq() {
        for (int i = 0; i < 20; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_SPORT.type());
            LotteryReq req = new LotteryReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(899L);
            req.setOwnerId(10001L);
            req.setBetId(System.currentTimeMillis() + i);
            req.setBcBetId(System.currentTimeMillis() + i * 100);
            req.setGameId(100L +i);
            req.setInsertDatetime(System.currentTimeMillis());
            req.setUpdateDatetime(System.currentTimeMillis());
            req.setBetDatetime(System.currentTimeMillis());
            req.setBetAmount(100L);
            req.setValidBetAmount(1000L);
            req.setPayoff(10L);

            req.setDetail("第201705060025期 特别号@48");
            req.setLotteryType("重庆时时彩");
            req.setPlayType("特别号");


            jsonObject.put("Record", req);
            template.send("LOTTERY", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }

        for (int i = 0; i < 20; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_SPORT.type());
            LotteryReq req = new LotteryReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(512L);
            req.setOwnerId(10001L);
            req.setBetId(System.currentTimeMillis() + i);
            req.setBcBetId(System.currentTimeMillis() + i * 100);
            req.setGameId(100L +i);
            req.setInsertDatetime(System.currentTimeMillis());
            req.setUpdateDatetime(System.currentTimeMillis());
            req.setBetDatetime(System.currentTimeMillis());
            req.setBetAmount(100L);
            req.setValidBetAmount(1000L);
            req.setPayoff(10L);

            req.setDetail("第201705060025期 特别号@48");
            req.setLotteryType("重庆时时彩");
            req.setPlayType("特别号");


            jsonObject.put("Record", req);
            template.send("LOTTERY", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(req));
        }
    }

    @Test
    public void testEGameReq() {
        for (int i = 0; i < 2; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("DataType", KafkaConf.DataType.PLUTUS_EGAME.type());
            EGameReq req = new EGameReq();
            req.setReqId(System.currentTimeMillis() + i);
            req.setUserId(2000001L + i);
            req.setOwnerId(10001L);
            req.setBetId(System.currentTimeMillis() + i);
            req.setBcBetId(System.currentTimeMillis() + i * 100);
            req.setGameId(100L +i);
            req.setInsertDatetime(System.currentTimeMillis());
            req.setUpdateDatetime(System.currentTimeMillis());
            req.setBetDatetime(System.currentTimeMillis());
            req.setBetAmount(100L);
            req.setValidBetAmount(1000L);
            req.setPayoff(10L);

            req.setDetail("1：1");
            req.setResult("输");

            jsonObject.put(DATA, req);
            template.send("cruis_capital", JSON.toJSONString(jsonObject));
            System.out.println(JSON.toJSONString(jsonObject));
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
