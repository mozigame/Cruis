package org.crius.service;

import com.alibaba.fastjson.JSONObject;
import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.tools.IPUtil;
import com.magic.api.commons.utils.StringUtils;
import org.junit.After;
import org.junit.Test;

import java.util.*;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 16:17
 */
public class RandomTest {

    @Test
    public void getRandom() {
        System.out.println("1503626795143740532".length());
//        for (int i=0;i<20;i++) {
//            System.out.println(new Random().nextLong());;
//        }
    }

    @Test
    public void get() {
        System.out.println("1498039034244995028".length());
    }

    @Test
    public void getLength() {
        System.out.println("124298765456787654567".length());
        System.out.println("1498446482049861267");
    }

    @Test
    public void mod() {
        System.out.println(0x300008);
        System.out.println(Math.abs(new Long(13499871).hashCode()) % 1024);
    }

    @Test
    public void stringSplit() {
        String str = "354     \n" +
                "     576     \n" +
                "     121     \n" +
                "     6867    \n" +
                "     3457    \n" +
                "     27510   \n" +
                "     11      \n" +
                "     568     \n" +
                "     1055    \n" +
                "     1936    \n" +
                "     73097   \n" +
                "     88191   \n" +
                "     77708   \n" +
                "     9193    \n" +
                "     87441   \n" +
                "     49      \n" +
                "     418     \n" +
                "     84165   \n" +
                "     4216    \n" +
                "     78      \n" +
                "     5661    \n" +
                "     78      \n" +
                "     1674    \n" +
                "     384     \n" +
                "     73190   \n" +
                "     4184    \n" +
                "     90305   \n" +
                "     123     \n" +
                "     75248   \n" +
                "     24      \n" +
                "     431     \n" +
                "     80643   \n" +
                "     911     \n" +
                "     37      \n" +
                "     77512   \n" +
                "     210729  \n" +
                "     517     \n" +
                "     600     \n" +
                "     6016 ";

        String[] strs = str.split("\n");
        int a = 0;
        for (int i = 0; i < strs.length; i++) {
            String b = strs[i].trim();
            if (StringUtils.isNotBlank(b)) {
                a += Integer.parseInt(b);
            }
        }
        System.out.println(a);
//        System.out.println(str.replace("\n",","));

        System.out.println();
        System.out.println("--------------------");
        String as = " 6400210\n" +
                "15299972\n" +
                "11099965\n" +
                "12499967\n" +
                "11899963\n" +
                " 8749992\n" +
                " 6414851\n" +
                " 2612585\n" +
                " 5332967\n" +
                "14899975\n" +
                " 6243086\n" +
                "15899971\n" +
                "11899959\n" +
                " 6400206\n" +
                "12699953";
        String aas[] = as.split("\n");
        for (int i = 0; i < aas.length; i++) {
            System.out.print(aas[i] + ",");
        }

    }


    @Test
    public void listRem() {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        list.add(5L);

        Map<Long, Long> map = new HashMap<>();
        map.put(1L, 1L);
        map.put(2L, 2L);
        map.put(3L, 3L);
        map.put(4L, 4L);
        map.put(5L, 5L);
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            Long idP = 0L;
            for (Long id : list) {
                if (entry.getValue().equals(id)) {
                    idP = id;
                    break;
                }
            }
            if (idP > 0) {
                list.remove(idP);
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(DateUtil.formatDateTime(new Date(1504061831315L), "yyyyMMdd"));
    }

    @Test
    public void splitSpendTime() {
        String lotNum = "3,3,5";
        long startTime = System.currentTimeMillis();
        for (long i = 0; i < 10_0000_0000L; i++) {
//            String [] lt= lotNum.split(",");
//            List<>
//            Collections.sort(lt);
            if (1 == 2) {

            }
        }
        System.out.println(System.currentTimeMillis() - startTime);

    }

    @Test
    public void testJson() {
        String str = "{\"memberId\" : 2665170, \"memberName\" : \"drq8974\", \"agentId\" : 8603624, \"agentName\" : \"blrcompany_dl\", \"stockId\" : 10699997, \"ownerId\" : 10699997, \"registerTime\" : \"1501691771829\", \"updateTime\" : \"1502500709823\", \"status\" : 1, \"level\" : 54, \"depositCount\" : 0, \"withdrawCount\" : 1, \"depositMoney\" : 0, \"withdrawMoney\" : 505000, \"currencyType\" : 1, \"lastDepositMoney\" : 0, \"lastWithdrawMoney\" : 505000, \"maxDepositMoney\" : 0, \"maxWithdrawMoney\" : 505000 }\n" +
                "{\"memberId\" : 3444988, \"memberName\" : \"HV3bhF3X35\", \"agentId\" : 8603624, \"agentName\" : \"blrcompany_dl\", \"stockId\" : 10699997, \"ownerId\" : 10699997, \"registerTime\" : \"1501639050489\", \"updateTime\" : \"1501639050562\", \"status\" : 1, \"level\" : 54, \"depositCount\" : 0, \"withdrawCount\" : 0, \"depositMoney\" : 0, \"withdrawMoney\" : 0, \"currencyType\" : 1, \"lastDepositMoney\" : 0, \"lastWithdrawMoney\" : 0, \"maxDepositMoney\" : 0, \"maxWithdrawMoney\" : 0 }\n" +
                "{\"memberId\" : 4710100, \"memberName\" : \"uuv973\", \"agentId\" : 8603624, \"agentName\" : \"blrcompany_dl\", \"stockId\" : 10699997, \"ownerId\" : 10699997, \"registerTime\" : \"1501691749960\", \"updateTime\" : \"1501703161722\", \"status\" : 1, \"level\" : 54, \"depositCount\" : 0, \"withdrawCount\" : 0, \"depositMoney\" : 0, \"withdrawMoney\" : 0, \"currencyType\" : 1, \"lastDepositMoney\" : 0, \"lastWithdrawMoney\" : 0, \"maxDepositMoney\" : 0, \"maxWithdrawMoney\" : 0 }\n";
        String[] datas = str.split("\n");
        for (String data : datas) {

            JSONObject jo = JSONObject.parseObject(data);
            System.out.println(jo.get("memberId") + "\t" + jo.get("account") + "\t" + DateUtil.formatDateTime(new Date(jo.getLong("registerTime")), DateUtil.formatDefaultTimestamp) + "\t" +
                    DateUtil.formatDateTime(new Date(jo.getLong("loginTime")), DateUtil.formatDefaultTimestamp) + "\t" + jo.get("registerIp") + "\t" + jo.get("loginIp"));
        }


    }


    @Test
    public void splitId() {
        String str = "205846\n" +
                "469306\n" +
                "2665170\n" +
                "3413571\n" +
                "3444988\n" +
                "4551222\n" +
                "4654053\n" +
                "4710100\n" +
                "4803960\n" +
                "5359696\n" +
                "7155410\n" +
                "7799913\n" +
                "7885545\n" +
                "10899991\n" +
                "11099961\n" +
                "11099999\n" +
                "11621631\n" +
                "12087722\n" +
                "12498899\n" +
                "12699996\n" +
                "13018852\n" +
                "13420901\n" +
                "13799888\n" +
                "14098332\n" +
                "14099962\n" +
                "14217572\n" +
                "14221449\n" +
                "14294531\n" +
                "14494967\n" +
                "14499960\n" +
                "15014607\n" +
                "15087963\n" +
                "15669867\n" +
                "16021159\n" +
                "16081146\n" +
                "16095350\n" +
                "16267331\n" +
                "16288240";

        String str1 = "205846\n" +
                "469306\n" +
                "3413571\n" +
                "4551222\n" +
                "4654053\n" +
                "4803960\n" +
                "5359696\n" +
                "7155410\n" +
                "7799913\n" +
                "7885545\n" +
                "10899991\n" +
                "11099961\n" +
                "11099999\n" +
                "11621631\n" +
                "12087722\n" +
                "12498899\n" +
                "12699996\n" +
                "13018852\n" +
                "13420901\n" +
                "13799888\n" +
                "14098332\n" +
                "14099962\n" +
                "14217572\n" +
                "14221449\n" +
                "14294531\n" +
                "14494967\n" +
                "14499960\n" +
                "15014607\n" +
                "15087963\n" +
                "15669867\n" +
                "16021159\n" +
                "16081146\n" +
                "16095350\n" +
                "16267331\n" +
                "16288240\n";

        String[] ids = str.split("\n");

        String [] ids1= str1.split("\n");
        List<String> idList = Arrays.asList(ids1);
        for (String id : ids) {
            if (!idList.contains(id)) {
                System.out.println(id);
            }
        }
        System.out.println();
        System.out.println(ids.length);
    }
    @Test
    public void modTest() {
        System.out.println(2665170%100);
        System.out.println(3444988%100);
        System.out.println(4710100%100);

        System.out.println(DateUtil.formatDateTime(new Date(1502020840809L),DateUtil.formatDefaultTimestamp));
        System.out.println(DateUtil.formatDateTime(new Date(1502216670947L),DateUtil.formatDefaultTimestamp));
        System.out.println(DateUtil.formatDateTime(new Date(1502125159422L),DateUtil.formatDefaultTimestamp));

        System.out.println(IPUtil.intToIp(1903941681));
        System.out.println(IPUtil.intToIp(604343258));
        System.out.println(IPUtil.intToIp(-637395906));

    }
}
