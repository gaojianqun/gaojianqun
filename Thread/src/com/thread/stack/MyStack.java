package com.thread.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaojianqun on 2018/10/16.
 * 用线程模拟一个栈
 */
public class MyStack {

    private List list = new ArrayList();

    public synchronized void push(){
        try {
            while(list.size() == 1){
                this.wait();
            }
            list.add("anything:"+Math.random());
            this.notifyAll();
            System.out.println("push = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String pop(){
        String returnValue = "";
        try {
            while(list.size() == 0){
                System.out.println("pop操作中的："+Thread.currentThread().getName() + "线城呈wait状态");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            this.notifyAll();
            System.out.println("pop = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

}
