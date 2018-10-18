package com.thread.service2;

import com.thread.service.HasSelfPrivateNum;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class Run {

    public static void main(String [] args){
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();

        ThreadA aThread = new ThreadA(hasSelfPrivateNum);
        ThreadB bThread = new ThreadB(hasSelfPrivateNum);
        bThread.start();
        aThread.start();


    }

}
