package com.java.base;

import com.java.User;

import java.util.Date;

public class Test {

    public static void main(String [] args){
        User user = new User();
        user.setName("张三");
        user.setAge(13);

        Integer age = user.getAge() + 1;
        user.setAge(age);
        System.out.println(user.getAge());

        System.out.println(new Date().getTime());
    }

}
