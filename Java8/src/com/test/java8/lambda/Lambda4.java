package com.test.java8.lambda;

/**
 * Created by gaojianqun on 2018/5/30.
 */
public class Lambda4 {

    public static void main(String[] args){

        Runnable oldRunnalbe = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Old Runnable");
            }
        };

        Runnable newRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + ": New Lambda Runnable");
        };

        new Thread(oldRunnalbe).start();
        new Thread(newRunnable).start();
    }

}
