package com.spring.design.pattern.proxy;

public class Test {

    public static void main(String [] args){
        UserServiceImpl userService = new UserServiceImpl();
        UserService userProxy = (UserService) ProxyFactory.createProxy(userService);
        userProxy.add();
    }

}
