package com.spring.design.pattern.single;

/**
 * Created by gaojianqun on 2018/12/16.
 * 线程安全的单例设计模式(效率较高)
 * 双重校验锁
 */
public class Singleton4 {

    //懒汉式
    private static Singleton4 instance;

    //私有化构造器
    private Singleton4(){

    }

    //双重校验锁
    public static Singleton4 getInstance(){
        if(instance == null){
            synchronized (instance){
                if(instance == null){
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }

}
