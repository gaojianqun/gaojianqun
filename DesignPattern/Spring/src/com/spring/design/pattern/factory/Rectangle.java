package com.spring.design.pattern.factory;

/**
 * Created by gaojianqun on 2018/10/10.
 */
public class Rectangle implements Sharp{

    @Override
    public void draw(){
        System.out.println("Inside Rectangle::draw() method.");
    }

}
