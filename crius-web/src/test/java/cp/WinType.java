package cp;

import java.util.*;

/**
 * User: joey
 * Date: 2017/8/31
 * Time: 10:56
 * 五星玩法
 */
public class WinType {

    /**
     * 五星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean wuXingZhiXuanWin(String orderNum, String winNum, int playType) {
        return orderNum.equals(winNum);
    }


    /**
     * 五星直选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean wuXingZhiXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        if (orderNum.size() != winNum.size()) {
            return false;
        }
        for (int i = 0; i < orderNum.size(); i++) {
            if (!orderNum.get(i).equals(winNum.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 五星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean wuXingZuXuanWin(String orderNum, String winNum, int playType) {
        if (orderNum.length() != winNum.length()) {
            return false;
        }
        String [] orderNums = orderNum.split(",");
        String [] winNums = winNum.split(",");
        Arrays.sort(orderNums);
        Arrays.sort(winNums);
        for (int i=0; i < orderNums.length; i++) {
            if (!orderNums[i].equals(winNums[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 五星组选中奖方式
     *
     * @param orderNum
     * @param winNum
     * @param playType
     */
    public static boolean wuXingZuXuanWin(List<String> orderNum, List<String> winNum, int playType) {
        Collections.sort(orderNum);
        Collections.sort(winNum);
        if (orderNum.size() != winNum.size()) {
            return false;
        }
        for (int i = 0; i < orderNum.size(); i++) {
            if (!orderNum.get(i).equals(winNum.get(i))) {
                return false;
            }
        }
        return true;
    }

}
