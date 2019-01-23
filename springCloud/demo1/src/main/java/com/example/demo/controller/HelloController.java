package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.compnent.RedisClient;
import com.example.demo.domain.User;
import com.example.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xerial.snappy.Snappy;

import javax.annotation.Resource;
import java.io.IOException;


/**
 * Created by gaojianqun on 2018/9/26.
 */
@RestController
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private RedisService redisService;

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

    @GetMapping("/user/addUser")
    public String addUser(@RequestParam Long id,@RequestParam String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        try {
            byte[] msg = Snappy.compress(JSON.toJSONString(user));
            redisClient.set("user1",msg);
            redisClient.set("user2",user);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }

    @GetMapping("/user/getRedisUser")
    public String getRedisUser(@RequestParam String key){
        try {
            byte[] chars = (byte[]) redisClient.get(key);
            String str = Snappy.uncompressString(chars);
            return str;

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }

}
