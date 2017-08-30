package org.crius.service;

import com.magic.api.commons.tools.DateUtil;
import com.magic.api.commons.utils.StringUtils;
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
        String str="354     \n" +
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

        String [] strs = str.split("\n");
        int a = 0;
        for (int i=0 ;i< strs.length; i++) {
            String b = strs[i].trim();
            if (StringUtils.isNotBlank(b)) {
                a += Integer.parseInt(b);
            }
        }
        System.out.println(a);
//        System.out.println(str.replace("\n",","));

        System.out.println();
        System.out.println("--------------------");
        String as =" 6400210\n" +
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
        String aas [] =as.split("\n");
        for (int i=0;i<aas.length;i++) {
            System.out.print(aas[i]+",");
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

        Map<Long, Long> map =new HashMap<>();
        map.put(1L,1L);
        map.put(2L,2L);
        map.put(3L,3L);
        map.put(4L,4L);
        map.put(5L,5L);
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
        System.out.println(DateUtil.formatDateTime(new Date(1504061831315L),"yyyyMMdd"));
    }
}
