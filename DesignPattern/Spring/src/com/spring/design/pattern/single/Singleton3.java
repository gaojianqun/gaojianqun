package com.spring.design.pattern.single;

/**
 * Created by gaojianqun on 2018/12/16.
 * 线程安全的单例设计模式(效率较低)
 */
public class Singleton3 {

    //懒汉式
    private static Singleton3 instance;

    //私有化构造器
    private Singleton3(){

    }

    //创建方法加锁
    public static synchronized Singleton3 getInstance(){
        if(instance == null){
            instance = new Singleton3();
        }
        return instance;
    }

}
