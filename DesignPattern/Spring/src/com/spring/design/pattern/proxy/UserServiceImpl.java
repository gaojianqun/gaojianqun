package com.spring.design.pattern.proxy;

public class UserServiceImpl implements UserService{

    @Override
    public void add() {
        System.out.println("jdk add");
    }

}
