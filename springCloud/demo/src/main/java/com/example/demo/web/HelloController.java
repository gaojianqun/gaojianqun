package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by gaojianqun on 2018/2/7.
 */

@RestController
public class HelloController {

    @Value("${name:info}")
    String info;

    @RequestMapping("/")
    String info() {
        return info;
    }

    @GetMapping("/hello")
    public String index(){
       return "Hello World Gaojianqun";
    }

    @GetMapping(value = "/hello1")
    public String hello1(@RequestParam( value="name") String name){
        return "hello:" + name;
    }

}
