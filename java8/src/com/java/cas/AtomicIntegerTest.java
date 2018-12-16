package com.java.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gaojianqun on 2018/11/27.
 */
public class AtomicIntegerTest {

    public static void main(String [] args){
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int i = atomicInteger.get();
        for(int j = 0;j < 100;j++){
            atomicInteger.compareAndSet(i,i++);
        }
        System.out.println(i);
    }

}
