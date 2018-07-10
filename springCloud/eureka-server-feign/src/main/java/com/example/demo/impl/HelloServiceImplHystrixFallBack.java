package com.example.demo.impl;

import com.example.demo.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaojianqun on 2018/3/15.
 * 服务降级
 */

@Component
public class HelloServiceImplHystrixFallBack implements HelloService{
    @Override
    public String hello() {
        return "no hello world";
    }

    @Override
    public String hello1(@RequestParam( value="name") String name) {
        return "sorry error!";
    }
}
