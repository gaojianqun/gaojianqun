package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by gaojianqun on 2018/2/7.
 */

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index(){
       return "Hello World";
    }

    @GetMapping(value = "/hello1")
    public String hello1(@RequestParam( value="name") String name){
        return "hello:" + name;
    }

}
