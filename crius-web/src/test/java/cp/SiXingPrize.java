package cp;

import org.junit.Test;

import java.util.*;

/**
 * User: joey
 * Date: 2017/8/31
 * Time: 23:09
 */
public class SiXingPrize {

    enum SiXingPlayType {

        si_xing_zhi_xuan_fu_shi(10001, 1001, "四星直选复式"),
        si_xing_zhi_xuan_dan_shi(10002, 1001, "四星直选单式"),
        si_xing_zhi_xuan_zu_he(10003, 1002, "四星直选组合"),
        si_xing_zu_xuan_24(10004, 1003, "四星组选24"),
        si_xing_zu_xuan_12(10005, 1003, "四星组选12"),
        si_xing_zu_xuan_6(10006, 1003, "四星组选6"),
        si_xing_zu_xuan_4(10007, 1003, "四星组选4");

        private int playType;   //玩法类型
        private int winType;    //中奖类型
        private String desc;    //描述

        private static Map<Integer, SiXingPlayType> map = new HashMap<>();

        static {
            for (SiXingPlayType obj : SiXingPlayType.values()) {
                map.put(obj.getPlayType(), obj);
            }
        }

        SiXingPlayType(int playType, int winType, String desc) {
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

        public static SiXingPlayType parse(int playType) {
            return map.get(playType);
        }
    }

    /**
     * 四星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean siXingZhiXuanWin(String orderNum, String winNum, int playType) {
        return orderNum.equals(winNum.substring(2));
    }


    /**
     * 四星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean siXingZhiXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        if ((winNum.size() - 1) != orderNum.size()) {
            return false;
        }
        for (int i = 1; i < winNum.size(); i++) {
            if (!winNum.get(i).equals(orderNum.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 四星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean siXingZuXuanWin(String orderNum, String winNum, int playType) {
        if ((winNum.length() - orderNum.length()) != 2) {
            return false;
        }
        String[] orderNums = orderNum.split(",");
        String[] winNums = winNum.split(",");
        Arrays.sort(orderNums);
        Arrays.sort(winNums);
        for (int i = 1; i < orderNums.length; i++) {
            if (!orderNums[i].equals(winNums[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 四星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean siXingZuXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        Collections.sort(orderNum);
        Collections.sort(winNum);
        if ((winNum.size() - orderNum.size()) != 1) {
            return false;
        }
        for (int i = 1; i < winNum.size(); i++) {
            if (!winNum.get(i).equals(orderNum.get(i - 1))) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void siXingZhiXuan() {
        List<List<String>> list = new ArrayList<>();
        List<String> winNum = new ArrayList<>();
        List<List<String>> winList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            winNum.add(new Random().nextInt(10) + "");
        }
        System.out.println("win num is : " + winNum);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            List<String> strs = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                strs.add(new Random().nextInt(10) + "");
            }
            list.add(strs);
            if (list.size() % 100_0000 == 0) {
                System.out.println("strs.size : " + list.size() + ", spend Time : " + (System.currentTimeMillis() - startTime));
            }
        }
        long time = System.currentTimeMillis();
        for (List<String> orderNum : list) {
            if (SiXingPrize.siXingZhiXuanWin(orderNum, winNum, 10001)) {
                winList.add(orderNum);
                System.out.println("this num is won : " + orderNum + "");
            }
        }
        System.out.println("win size : " + winList.size() );
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - time);
    }

    @Test
    public void siXingZuXuanWin() {
        List<List<String>> list = new ArrayList<>();
        List<String> winNum = new ArrayList<>();
        List<List<String>> winList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            winNum.add(new Random().nextInt(10) + "");
        }
        System.out.println("win num is : " + winNum);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            List<String> strs = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                strs.add(new Random().nextInt(10) + "");
            }
            list.add(strs);
            if (list.size() % 100_0000 == 0) {
                System.out.println("strs.size : " + list.size() + ", spend Time : " + (System.currentTimeMillis() - startTime));
            }
        }
        long time = System.currentTimeMillis();
        for (List<String> orderNum : list) {
            if (SiXingPrize.siXingZuXuanWin(orderNum, winNum, 10001)) {
                winList.add(orderNum);
                System.out.println("this num is won : " + orderNum + "");
            }
        }
        System.out.println("win size : " + winList.size() );
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - time);
    }
}
