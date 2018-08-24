package com.thread.test;

import org.omg.CORBA.TIMEOUT;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Created by gaojianqun on 2018/8/17.
 */
public class InterruptedThread implements Runnable{
    @Override
    public void run() {
        while (true){

        }
    }

    public static void main(String [] args) throws Exception{
        Thread interruptedThread = new Thread(new InterruptedThread(),"InterruptedThread");
        interruptedThread.start();
        TimeUnit.SECONDS.sleep(2);
        interruptedThread.interrupt();
        System.out.println("InterruptedThread interrupted is " + interruptedThread.isInterrupted());
        TimeUnit.SECONDS.sleep(2);
    }

}
