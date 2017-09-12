package cp;

import org.junit.Test;

import java.util.*;

/**
 * User: joey
 * Date: 2017/9/1
 * Time: 0:02
 */
public class SanXingPrize {

    enum SanXingPlayType{

        san_xing_zhi_xuan_fu_shi(10001, 1001, "三星直选复式"),
        san_xing_zhi_xuan_dan_shi(10002, 1001, "三星直选单式"),
        san_xing_z_xuan_6(10003, 1002, "三星组6"),
        san_xing_z_xuan_3(10004, 1002, "三星组3"),

        san_xing_zu_xuan_60(10005, 1003, "五星组选60"),
        san_xing_zu_xuan_30(10006, 1003, "五星组选30"),
        san_xing_zu_xuan_10(10007, 1003, "五星组选10"),
        san_xing_zu_xuan_5(10008, 1003, "五星组选5");

        private int playType;   //玩法类型
        private int winType;    //中奖类型
        private String desc;    //描述

        private static Map<Integer, SanXingPlayType> map = new HashMap<>();

        static {
            for (SanXingPlayType obj : SanXingPlayType.values()) {
                map.put(obj.getPlayType(), obj);
            }
        }

        SanXingPlayType(int playType, int winType, String desc) {
            this.playType = playType;
            this.winType = winType;
            this.desc = desc;
        }

        public int getPlayType() {
            return playType;
        }

        public void setPlayType(int playType) {
            this.playType = playType;
        }

        public int getWinType() {
            return winType;
        }

        public void setWinType(int winType) {
            this.winType = winType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static SanXingPlayType parse(int playType) {
            return map.get(playType);
        }

    }


    /**
     * 三星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean sanXingZhiXuanWin(String orderNum, String winNum, int playType) {
        return orderNum.equals(winNum.substring(4));
    }


    /**
     * 三星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean sanXingZhiXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        if ((orderNum.size() - winNum.size()) != 2) {
            return false;
        }
        for (int i = 2; i < orderNum.size(); i++) {
            if (!orderNum.get(i).equals(winNum.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 三星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean sanXingZuXuanWin(String orderNum, String winNum, int playType) {
        if ((winNum.length() - orderNum.length()) != 4) {
            return false;
        }
        String[] orderNums = orderNum.split(",");
        String[] winNums = winNum.split(",");
        Arrays.sort(orderNums);
        Arrays.sort(winNums);
        for (int i = 2; i < orderNums.length; i++) {
            if (!orderNums[i].equals(winNums[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 三星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean sanXingZuXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        Collections.sort(orderNum);
        Collections.sort(winNum);
        if ((orderNum.size() - winNum.size()) != 2) {
            return false;
        }
        for (int i = 2; i < orderNum.size(); i++) {
            if (!orderNum.get(i).equals(winNum.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testSanXingZhiXuan() {

    }
}
