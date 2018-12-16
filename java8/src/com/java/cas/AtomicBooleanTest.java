package com.java.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by gaojianqun on 2018/11/27.
 */
public class AtomicBooleanTest implements Runnable{

    public static AtomicBoolean exits = new AtomicBoolean(true);

    public static void main(String [] args){
        AtomicBooleanTest abd = new AtomicBooleanTest();
        Thread thread1 = new Thread(abd);
        Thread thread2 = new Thread(abd);

        thread1.start();
        thread2.start();

    }

    @Override
    public void run() {
        System.out.println("begin run");
        System.out.println("real " + exits.get());
        if(exits.compareAndSet(true,false)){
            System.out.println(Thread.currentThread().getName() + "  " + exits.get() );
            exits.set(true);
        }else{
            run();
        }
    }
}
