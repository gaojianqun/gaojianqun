package com.thread.service;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class ThreadA extends Thread{

    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef){
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }

}
