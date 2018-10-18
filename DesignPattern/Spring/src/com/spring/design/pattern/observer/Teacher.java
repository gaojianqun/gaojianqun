package com.spring.design.pattern.observer;


import java.util.Observable;

/**
 * Created by gaojianqun on 2018/10/17.
 * 观察者模式：
 * 发布/订阅(Publish/Subscribe)模式
 * 被观察者
 */
public class Teacher extends Observable {

    //布置作业的信息
    private String info;

    public void setHomeWork(String info){
        this.info = info;
        System.out.println("布置的作业是"+info);
        //设置状态为被观察者
        setChanged();
        //通知所有观察者要接受消息
        notifyObservers();
    }

    public String getInfo(){
        return info;
    }

}
