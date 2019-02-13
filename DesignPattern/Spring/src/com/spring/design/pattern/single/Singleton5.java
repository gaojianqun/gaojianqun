package com.spring.design.pattern.single;

/**
 * Created by gaojianqun on 2018/12/16.
 * 静态内部类实现
 */
public class Singleton5 {

    //私有化构造器
    private Singleton5(){

    }

    //对外提供公共的访问方法
    public static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }

    //静态内部类，里面实例化外部类
    private static class SingletonHolder{
        public final static Singleton5 instance = new Singleton5();
    }

}
