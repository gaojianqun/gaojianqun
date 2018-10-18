package com.spring.design.pattern.factory;

/**
 * Created by gaojianqun on 2018/10/10.
 */
public class Color implements Sharp{

    @Override
    public void draw(){
        System.out.println("Inside Color::draw() method.");
    }

}
