package com.thread.service2;

import com.thread.service.HasSelfPrivateNum;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class ThreadB extends Thread{

    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef){
        super();
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }

}
