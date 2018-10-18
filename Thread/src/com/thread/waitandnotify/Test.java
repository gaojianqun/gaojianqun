package com.thread.waitandnotify;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class Test {

    public static void main(String [] args){

        Object lock = new Object();
        MyThread1 t1 = new MyThread1(lock);
        Thread thread1 = new Thread(t1);
        thread1.start();
        MyThread2 t2 = new MyThread2(lock);
        Thread thread2 = new Thread(t2);
        thread2.start();

    }

}
