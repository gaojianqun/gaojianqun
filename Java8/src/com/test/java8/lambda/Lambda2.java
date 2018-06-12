package com.test.java8.lambda;

/**
 * Created by gaojianqun on 2018/5/29.
 */
public class Lambda2 {

    final static String salutation = "Hello!";

    public static void main(String[] args){
        GreetingService greetingService1 = message ->
                System.out.println(salutation+message);
        greetingService1.sayMessage("World!");
    }

    interface GreetingService{
        void sayMessage(String message);
    }
}
