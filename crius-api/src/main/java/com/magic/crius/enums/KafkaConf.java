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


    public static final String TOPICS [] = new String[]{"plutus", "hera"};

    public static final String DATA = "Data";

    public static final String DATA_TYPE="DataType";

    public static final String CAPITAL_GROUP = "capital_group";
    /**
     * kafka 游戏中的数据
     */
    public static final String RECORD = "Record";


   

    /**
     * kafka消费的数据类型
     */
    public enum DataType {
        PLUTUS_ONL_CHARGE(0x3001, "线上入款"),    // 线上入款 12289
        PLUTUS_CMP_CHARGE(0x3002, "公司入款"),    // 公司入款 12290
        PLUTUS_DISCOUNT(0x3003, "优惠赠送"),  // 优惠赠送   12291
        PLUTUS_USER_WITHDRAW(0x3004, "会员出款"), // 会员出款   12292
        PLUTUS_OPR_WITHDRAW(0x3005, "人工提出"),  // 人工提出   12293
        PLUTUS_OPR_CHARGE(0x3006, "人工入款"),    // 人工入款   12294
        PLUTUS_CAHSBACK(0x3007, "给予返水"),  // 给予返水 12295
        PLUTUS_PAYOFF(0x3008, "派彩"),    // 派彩 12296
        PLUTUS_JP(0x3009, "彩金"),    // 彩金 12297
        PLUTUS_DS(0x300A, "打赏"),    //打赏  12298
        UPDATE_USER_LEVEL(0x300B, "会员升级"),  //会员升级 12299
        
        PLUTUS_OWNER_BILL(0x300C, "业主包网"),  //业主包网 12300
        
        PLUTUS_AGENT_BILL(0x300D, "代理退佣"),  //代理退佣 12301
        
        
        PLUTUS_LOTTERY(0x300E, "彩票"),    //彩票  12302
        PLUTUS_SPORT(0x300F, "体育"),    //体育  12303
        PLUTUS_VGAME(0x3011, "视讯"),    //视讯  12305
        PLUTUS_EGAME(0x3012, "电子"),   //电子  12306
        TAX_COUNT(0x4013,"会员扣款")     //会员扣款 16403
        ;    

        private static Map<Integer, DataType> map = new HashMap<>();

        static {
            for (DataType kafkaType : DataType.values()) {
                map.put(kafkaType.type(), kafkaType);
            }
        }

        private Integer type;

        private String typeName;

        DataType(Integer type,String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        public Integer type() {
            return type;
        }
        public String typeName() {
            return typeName;
        }

        public static DataType parse(Integer type) {
            return map.get(type);
        }
    }
}
