package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * User: joey
 * Date: 2017/5/29
 * Time: 16:32
 * kafka 离线数据的type类型
 */
public class KafkaConf {


    public static final String DATA = "Data";

    public static final String DATA_TYPE="DataType";

    /**
     * kafka消费的数据类型
     */
    public enum DataType {
        PLUTUS_ONL_CHARGE(0x3001),    // 在线充值 12289
        PLUTUS_CMP_CHARGE(0x3002),    // 公司入款 12290
        PLUTUS_DISCOUNT(0x3003),  // 优惠赠送   12291
        PLUTUS_USER_WITHDRAW(0x3004), // 用户提现   12292
        PLUTUS_OPR_WITHDRAW(0x3005),  // 人工提现   12293
        PLUTUS_OPR_CHARGE(0x3006),    // 人工入款   12294
        PLUTUS_CAHSBACK(0x3007),  // 返水 12295
        PLUTUS_PAYOFF(0x3008),    // 派彩 12296
        PLUTUS_JP(0x3009),    // 彩金 12297
        PLUTUS_DS(0x300A),    //打赏  12298
        PLUTUS_LOTTERY(0x300A),    //彩票  12299
        PLUTUS_SPORT(0x300B),    //体育  12300
        PLUTUS_VGAME(0x300C),    //视讯  12301
        PLUTUS_EGAME(0x300D);    //电子  12302

        private static Map<Integer, DataType> map = new HashMap<>();

        static {
            for (DataType kafkaType : DataType.values()) {
                map.put(kafkaType.type(), kafkaType);
            }
        }

        private Integer type;

        DataType(Integer type) {
            this.type = type;
        }

        public Integer type() {
            return type;
        }

        public static DataType parse(Integer type) {
            return map.get(type);
        }
    }

    /**
     * kafka消费的group
     */
    public enum Group {
        CAPITAL_GROUP;  //资金
    }

    public static void main(String[] args) {
//        12296
        for (DataType type : DataType.values()) {
            System.out.println(type.name() + "  " + type.type());
        }
//        System.out.println(0x3001);
//        System.out.println(0x3002);
//        System.out.println(0x3003);
//        System.out.println(0x3004);
//        System.out.println(0x3005);
//        System.out.println(0x3006);
//        System.out.println(0x3007);
//        System.out.println(0x3008);
//        System.out.println(0x3009);
//        System.out.println(0x300A);
//        System.out.println(0x300B);
//        System.out.println(0x300C);
//        System.out.println(0x300D);
//        System.out.println(0x300E);
//        System.out.println(0x300F);
    }

}
