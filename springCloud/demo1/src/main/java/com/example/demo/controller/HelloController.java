package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



/**
 * Created by gaojianqun on 2018/9/26.
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user")
    public User findUserById(){
        return restTemplate.getForObject("http://localhost:2010/greeting",User.class);
    }

    @GetMapping("/greeting")
    public User greeting() {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
//        JSONObject userJson = (JSONObject) JSON.toJSON(User.class);
//        "Greeting , "+ userJson;
        return user;
    }

}
