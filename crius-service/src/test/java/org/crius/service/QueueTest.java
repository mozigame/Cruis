package org.crius.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * User: joey
 * Date: 2017/9/9
 * Time: 1:08
 */
public class QueueTest {

    @Test
    public void getBaseOrderQueue() {
       int a=0;
        if (++a > 0) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }

    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> strings = new ConcurrentLinkedDeque<>();
        new Thread() {
            @Override
            public void run() {
                for (int i= 0;i< 100;i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    strings.add(i+"");
                }
            }
        }.start();
        System.out.println(JSON.toJSONString(strings));
        while (strings.size() > 0) {
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(strings.poll());
        }
        System.out.println(JSON.toJSONString(strings));
    }
}
