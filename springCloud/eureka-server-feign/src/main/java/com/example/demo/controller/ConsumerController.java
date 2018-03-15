package com.example.demo.controller;

import com.example.demo.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gaojianqun on 2018/3/13.
 */

@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "hello")
    public String helloConsumer(){
        return helloService.hello();
    }

    @GetMapping(value = "hello1")
    public String helloConsumer1(@RequestParam( value="name") String name){
        return helloService.hello1(name);
    }

}
