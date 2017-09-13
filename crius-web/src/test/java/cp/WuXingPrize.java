package cp;

import org.junit.Test;

import java.util.*;

/**
 * User: joey
 * Date: 2017/8/31
 * Time: 23:08
 */
public class WuXingPrize {

    enum WuXingPlayType{

        wu_xing_zhi_xuan_fu_shi(10001, 1001, "五星直选复式"),
        wu_xing_zhi_xuan_dan_shi(10002, 1001, "五星直选单式"),
        wu_xing_zhi_xuan_zu_he(10003, 1002, "五星直选组合"),
        wu_xing_zu_xuan_120(10004, 1003, "五星组选120"),
        wu_xing_zu_xuan_60(10005, 1003, "五星组选60"),
        wu_xing_zu_xuan_30(10006, 1003, "五星组选30"),
        wu_xing_zu_xuan_10(10007, 1003, "五星组选10"),
        wu_xing_zu_xuan_5(10008, 1003, "五星组选5");

        private int playType;   //玩法类型
        private int winType;    //中奖类型
        private String desc;    //描述

        private static Map<Integer, WuXingPlayType> map = new HashMap<>();

        static {
            for (WuXingPlayType obj : WuXingPlayType.values()) {
                map.put(obj.getPlayType(), obj);
            }
        }

        WuXingPlayType(int playType, int winType, String desc) {
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

        public static WuXingPlayType parse(int playType) {
            return map.get(playType);
        }

    }

    /**
     * 五星直选复式中奖
     */
    @Test
    public void wu_xing_zhi_xuan_fu_shi() {
        List<List<String>> list = new ArrayList<>();
        List<String> winNum = new ArrayList<>();
        List<List<String>> winList = new ArrayList<>(4000_0000);
        for (int i = 0; i < 5; i++) {
            winNum.add(new Random().nextInt(10) + "");
        }
        System.out.println("win num is : " + winNum);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1500_0000; i++) {
            List<String> strs = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                strs.add(new Random().nextInt(10) + "");
            }
            list.add(strs);
            if (list.size() % 100_0000 == 0) {
                System.out.println("strs.size : "+ list.size()+", spend Time : "+(System.currentTimeMillis() - startTime));
            }
        }
        long time = System.currentTimeMillis();
        for (List<String> orderNum : list) {
            if (WinType.wuXingZhiXuanWin(orderNum, winNum, 10001)) {
                winList.add(orderNum);
                System.out.println("this num is won : " + orderNum + "");
            }
        }
        System.out.println("win size : " + winList.size()+", list.size : "+list.size());
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - time);
    }


    /**
     * 五星组选中奖
     */
    @Test
    public void wu_xing_zu_xuan() {
        List<List<String>> list = new ArrayList<>();
        List<String> winNum = new ArrayList<>();
        List<List<String>> winList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            winNum.add(new Random().nextInt(10) + "");
        }
        System.out.println("win num is : " + winNum);
        for (int i = 0; i < 1000_0000; i++) {
            List<String> strs = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                strs.add(new Random().nextInt(10) + "");
            }
            list.add(strs);
        }
        long time = System.currentTimeMillis();
        for (List<String> orderNum : list) {
            if (WinType.wuXingZuXuanWin(orderNum, winNum, 10001)) {
                winList.add(orderNum);
                System.out.println("this num is won : " + orderNum + "");
            }
        }
        System.out.println("win size : " + winList.size());
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - time);
    }


}
