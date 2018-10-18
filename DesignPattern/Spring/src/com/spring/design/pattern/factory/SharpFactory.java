package com.spring.design.pattern.factory;

/**
 * Created by gaojianqun on 2018/10/10.
 *
 */
public class SharpFactory {

    //使用 getShape 方法获取形状类型的对象
    public Sharp getSharp(String sharp){
        if("RECTANGLE".equalsIgnoreCase(sharp)){
            return new Rectangle();
        }else if("COLOR".equalsIgnoreCase(sharp)){
            return new Color();
        }else if("SQUARE".equalsIgnoreCase(sharp)){
            return new Square();
        }else{
            return null;
        }
    }

}
