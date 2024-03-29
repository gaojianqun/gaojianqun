package com.spring.design.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by gaojianqun on 2018/10/17.
 */
public class Student implements Observer{

    private Observable ob;

    private String name;

    public Student(String name,Observable ob){
        this.ob = ob;
        this.name = name;
        ob.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Teacher t=(Teacher)o;
        System.out.println(name+"得到作业信息:"+t.getInfo());
    }
}
