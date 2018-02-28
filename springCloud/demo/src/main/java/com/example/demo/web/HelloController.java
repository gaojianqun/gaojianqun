package com.example.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
