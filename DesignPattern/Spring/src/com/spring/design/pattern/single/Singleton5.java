package com.spring.design.pattern.single;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class Singleton5 {

    private static class SingletonHolder{
        public final static Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance(){
        return SingletonHolder.instance;
    }

}
