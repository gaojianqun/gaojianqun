package com.java.unsafe;

import sun.misc.Unsafe;

import java.io.*;
import java.util.Arrays;

/**
 * Created by gaojianqun on 2018/11/26.
 * Unsafe类的简单使用
 */
public class Test1 {

    public static void main(String [] args) throws Exception{
        try {
            //使用unsafe创建一个对象
            //非常规的对象实例化。
            //allocateInstance()方法提供了另一种创建实例的途径。通常我们可以用new或者反射来实例化对象，使用allocateInstance()方法可以直接生成对象实例，且无需调用构造方法和其它初始化方法。
            //这在对象反序列化的时候会很有用，能够重建和设置final字段，而不需要调用构造方法。
            String string =  (String) Unsafe.getUnsafe().allocateInstance(String.class);
            string = "11111";
            System.out.println(string);

        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
