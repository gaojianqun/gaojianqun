package com.thread.test;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaojianqun on 2018/8/17.
 */
public class InterruptedException implements Runnable{
    @Override
    public void run() {

    }

    public static void main(String [] args) throws Exception{

        Thread interruptedThread = new Thread(new InterruptedException(),"interruptedThread");
        interruptedThread.start();
        TimeUnit.SECONDS.sleep(2);

        // 中断被阻塞状态（sleep、wait、join 等状态）的线程，会抛出异常 InterruptedException
        // 在抛出异常 InterruptedException 前，JVM 会先将中断状态重置为默认状态 false
        interruptedThread.interrupt();
        System.out.println("InterruptedThread interrupted is " + interruptedThread.isInterrupted());
        TimeUnit.SECONDS.sleep(2);

    }
}
