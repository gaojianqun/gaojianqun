package com.thread;

/**
 * Created by gaojianqun on 2018/8/15.
 */
public class MyThreadBrother implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread 的线程对象正在执行任务");
    }

    public static void main(String[] args){
        for(int i = 0;i < 10;i++){
            Thread thread = new Thread(new MyThreadBrother());
            thread.start();
            System.out.println("MyThreadBrother 的线程对象" + thread.getId());
        }
    }
}
