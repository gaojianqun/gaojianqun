package com.example.demo.controller;

import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by gaojianqun on 2018/3/13.
 */

@RestController
public class DcController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-provider.host}")
    private String serviceProviderHost;

    @Value("${service-provider.port}")
    private Integer serviceProviderPort;

    @Value("${service-provider.name}")
    private String serviceProviderName;

    @GetMapping("/consumer")
    public String dc() {
        User user = new User();
        user.setId(1L);
        user.setName("高建群");
        return restTemplate.getForObject("http://" +
                        serviceProviderName +
                        "/greeting",String.class);
    }

}
