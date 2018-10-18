package com.thread.waitandinterrupt;


/**
 * Created by gaojianqun on 2018/10/16.
 */
public class ThreadA implements Runnable{

    private Object lock;

    public ThreadA(Object lock){
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        Service service = new Service();
        service.testMethod(lock);
    }

}
