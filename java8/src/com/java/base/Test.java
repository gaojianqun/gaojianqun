package com.java.base;

import com.java.User;

public class Test {

    public static void main(String [] args){
        User user = new User();
        user.setName("张三");
        user.setAge(13);

        Integer age = user.getAge() + 1;
        user.setAge(age);
        System.out.println(user.getAge());
    }

}
