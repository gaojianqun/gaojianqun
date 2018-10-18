package com.thread.waitandnotify;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class MyThread1 implements Runnable{

    private Object lock;

    public MyThread1(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            try {
                System.out.println("程序开始了。。。。。。");
                lock.wait(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
