package com.example.demo;

import com.example.demo.impl.HelloServiceImplHystrixFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaojianqun on 2018/3/15.
 */

@FeignClient(name = "hello-service",fallback = HelloServiceImplHystrixFallBack.class)
public interface HelloService {

    /**
     * feign组件定义下的接口，提供给此消费者的远程调用接口
     * @return
     */
    @GetMapping(value="/hello")
    String hello();

    @GetMapping(value="/hello1")
    String hello1(@RequestParam( value="name") String name);

}
