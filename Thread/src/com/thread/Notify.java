package com.thread;

import java.lang.*;
import java.lang.InterruptedException;

/**
 * Created by gaojianqun on 2018/10/22.
 */
public class Notify {

    private static volatile Object resourceA = new Object();

    public static void main(String [] args) throws InterruptedException{
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA共享资源的监视器锁
                synchronized (resourceA){
                    try {
                        System.out.println("threadA get resourceA lock");
                        resourceA.wait();
                        System.out.println("threadA end wait");
                    } catch (java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA共享资源的监视器锁
                synchronized (resourceA){
                    try {
                        System.out.println("threadB get resourceA lock");
                        resourceA.wait();
                        System.out.println("threadB end wait");
                    } catch (java.lang.InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (resourceA){
                    System.out.println("threadC begin notify");
                }
                resourceA.notifyAll();
            }
        });

        //启动线程
        threadA.start();
        threadB.start();
        Thread.sleep(1000);
        threadC.start();
        //等待线程结束
        threadA.join();
        threadB.join();
        threadC.join();
        System.out.println("main over");

    }

}
