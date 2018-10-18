package com.spring.design.pattern.factory;

/**
 * Created by gaojianqun on 2018/10/10.
 */
public class Square implements Sharp{

    @Override
    public void draw(){
        System.out.println("Inside Square::draw() method.");
    }

}
