package com.thread;

/**
 * Created by gaojianqun on 2018/10/22.
 * 防止多个线程调用变量的发生变化
 */
public class ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<String>();

    static void print(String str){
        System.out.println(str + ":" + localVariable.get());
        localVariable.remove();
    }

    public static void main(String [] args){

        //设置One中本地变量localVariable的值
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadOne local variable");
                //调用打印函数
                print("threadOne");
                //打印本地变量值
                System.out.println("threadOne remove after"+":"+localVariable.get());
            }
        });

        //设置Two中本地变量localVariable的值
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadTwo local variable");
                //调用打印函数
                print("threadTwo");
                //打印本地变量值
                System.out.println("threadTwo remove after"+":"+localVariable.get());
            }
        });

        threadOne.start();
        threadTwo.start();
    }

}
