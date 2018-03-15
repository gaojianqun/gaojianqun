package com.example.demo.web;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.POST;

/**
 * Created by gaojianqun on 2018/3/14.
 */

@RestController
public class ServiceProviderController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/greeting")
    public String greeting() {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        return "Greeting , "+ user.toString() +" on port : "+port;
    }

}
