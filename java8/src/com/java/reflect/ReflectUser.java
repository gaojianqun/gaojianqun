package com.java.reflect;

import java.lang.reflect.Field;

/**
 * Created by gaojianqun on 2018/11/20.
 */
public class ReflectUser<T> {

    // model's Class
    protected Class<T> entityClass;

    // model's ClassName
    protected String entityClassName;

    public static void main(String [] args){
        User user = new User("1","Tom",1000000F);
        //获取类
        Class clazz = user.getClass();
        try {
            //获取私有化属性
            Field f1 = clazz.getDeclaredField("userName");
            f1.setAccessible(true);
            //使用反射机制可以打破封装性,导致了java对象的属性不安全
            Object obj = clazz.newInstance();
            f1.set(obj,"Bob");

            System.out.println(f1.get(user));
            System.out.println(f1.get(obj));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

}
