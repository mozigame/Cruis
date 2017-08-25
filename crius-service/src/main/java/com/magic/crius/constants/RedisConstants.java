package com.magic.crius.constants;

/**
 * RedisConstants
 *
 * @author zjh
 * @date 2017/5/10
 */
public class RedisConstants {

    public static final int EXPIRE_ONE_DAY = 60 * 60 * 24;
    /* 3 小时 */
    public static final int EXPIRE_THREE_HOUR =10800;


    /*定时拉取游戏列表的锁*/
    public static final String GAME_INFO_LOCK = "game_info_lock_";
    public static final String USER_LEVEL_LOCK = "user_level_lock_";
    /* 20分钟 */
    public static final int GAME_INFO_LOCK_EXPIRE= 1200;
    /**
     * 批量pop redis中的数据条数
     */
    public static final int BATCH_POP_NUM = 100;

    public static final String OWNER_BILL_KEY = "OWNER_BILL_KEY";

    /**
     * 同步用户数据每页批量
     */
    public static final int USER_INFO_ASYNC_PAGE_BATCH=2000;
    /**
     * 同步用户数据的页数
     */
    public static final String REDIS_USER_INFO_SYNC_PAGE="_userInfoSyncPage";

    public static final String REDIS_PROXY_INFO_SYNC_PAGE="_proxyInfoSyncPage";

    public static final String REDIS_USER_LEVEL_UPDATE_COUNT="_userLevelUCount";

    /**
     * 获取未处理的数据的页码
     */
    private static final String NO_PROC_PAGE="no_p_";
    /**
     * 获取未处理的数据的页码
     * @param prefix
     * @return
     */
    public static String getNoProcPage(CLEAR_PREFIX prefix, String hhDate) {
        return NO_PROC_PAGE + prefix.key() + hhDate;
    }

    /**
     * 是否执行定时任务
     */
    public static final String SCHEDULE_SWITCH="cri_sche_switch";


    /**
     * @doc
     */
    public enum CLEAR_PREFIX {
        PLUTUS_ONL_CHARGE("p_onl_charge_"),    // 用户充值
        PLUTUS_CMP_CHARGE("p_cmp_charge_"),    // 公司入款
        PLUTUS_DISCOUNT("p_discount_"),  // 优惠赠送
        PLUTUS_USER_WITHDRAW("p_user_withdraw_"), // 用户提现
        PLUTUS_OPR_WITHDRAW("p_opr_withdraw_"),  // 人工提现
        PLUTUS_OPR_CHARGE("p_opr_charge_"),    // 人工入款
        PLUTUS_CAHSBACK("p_cahsback_"),  // 返水
        PLUTUS_PAYOFF("p_payoff_"),    // 派彩
        PLUTUS_JP("p_jp_"),    // 彩金
        PLUTUS_DS("p_ds_"),    //打赏
        PLUTUS_LOTTERY("p_lottery"), //彩票
        PLUTUS_SPORT("p_sport"), //体育
        PLUTUS_VGAME("p_vgame"), //视讯
        PLUTUS_EGAME("p_egame"), //电子
        PLUTUS_BASE_GAME("p_base_game"); //注单



        private String prefix;

        CLEAR_PREFIX(String prefix) {
            this.prefix = prefix;
        }

        public String key() {
            return prefix;
        }

        public String key(long id) {
            return prefix + id;
        }

        public String key(String date) {
            return prefix + date;
        }

    }

}
