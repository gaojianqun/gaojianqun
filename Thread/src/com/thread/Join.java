package com.thread;

import java.lang.*;
import java.lang.InterruptedException;

/**
 * Created by gaojianqun on 2018/10/22.
 */
public class Join {

    public static void main(String[] args){
        //线程one
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadOne begin run!");
                for(;;){

                }
            }
        });

        //获取主线程
        final Thread mainThread = Thread.currentThread();

        //线程two
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                //休眠1s
                try {
                    Thread.sleep(1000);
                } catch (java.lang.InterruptedException e) {
                    e.printStackTrace();
                }

                //中断主线程
                mainThread.interrupt();
            }
        });

        //启动子线程
        threadOne.start();

        //延迟1s启动线程
        threadTwo.start();

        try {
            threadOne.join();
        } catch (InterruptedException e) {
            System.out.println("main thread:" + e);
        }

    }

}
