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

    /**
     * kafka消费的数据类型
     */
    public enum DataType{
        PLUTUS_ONL_CHARGE("iota" + 0x3001),    // 在线充值
        PLUTUS_CMP_CHARGE("iota" + 0x3002),    // 公司入款
        PLUTUS_DISCOUNT("iota" + 0x3003),  // 优惠赠送
        PLUTUS_USER_WITHDRAW("iota" + 0x3004), // 用户提现
        PLUTUS_OPR_WITHDRAW("iota" + 0x3005),  // 人工提现
        PLUTUS_OPR_CHARGE("iota" + 0x3006),    // 人工入款
        PLUTUS_CAHSBACK("iota" + 0x3007),  // 返水
        PLUTUS_PAYOFF("iota" + 0x3008),    // 派彩
        PLUTUS_JP("iota" + 0x3009),    // 彩金
        PLUTUS_DS("iota" + 0x300A);    //打赏

        private static Map<String, DataType> map = new HashMap<>();
        static {
            for (DataType kafkaType : DataType.values()) {
                map.put(kafkaType.getType(), kafkaType);
            }
        }
        private String type;

        DataType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public static DataType parse(String type) {
            return map.get(type);
        }
    }

    /**
     * kafka消费的group
     */
    public enum Group {
        CAPITAL_GROUP;  //资金
    }


}
