package com.spring.design.pattern.single;

/**
 * Created by gaojianqun on 2018/10/10.
 * 懒汉式
 */
public class Singleton {

    private static Singleton instance;

    private Singleton(){

    }

    public static Singleton getInstance(){
        if (instance != null){
            instance = new Singleton();
        }
        return instance;
    }

}
