package com.thread.waitandnotify;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class MyThread2  implements Runnable{

    private Object lock;

    public MyThread2(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock){
            lock.notify();
            System.out.println("程序结束了。。。。。。");
        }
    }

}
