package com.thread.waitandinterrupt;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class Test {

    public static void main(String[] args){

        Object lock = new Object();

        ThreadA threadA = new ThreadA(lock);

        Thread thread = new Thread(threadA);

        thread.start();

        try {
            Thread.sleep(500L);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
