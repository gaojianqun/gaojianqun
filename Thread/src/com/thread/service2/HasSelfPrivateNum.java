package com.thread.service2;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class HasSelfPrivateNum {

    //共享资源
    private int num = 0;

    synchronized public void addI(String userName){
        //非共享资源
//        int num = 0;
        if("a".equals(userName)){
            num = 100;
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("a set over !");
        }else {
            num = 200;
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("b set over !");
        }

        System.out.println(userName + "num:" + num);
    }

}
