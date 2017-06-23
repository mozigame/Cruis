package org.crius.service;

import org.junit.Test;

import java.util.Random;

/**
 * User: joey
 * Date: 2017/6/7
 * Time: 16:17
 */
public class RandomTest {

    @Test
    public void getRandom() {
        for (int i=0;i<20;i++) {
            System.out.println(new Random().nextLong());;
        }
    }
    @Test
    public void get() {
        System.out.println("1498039034244995028".length());
    }
}
