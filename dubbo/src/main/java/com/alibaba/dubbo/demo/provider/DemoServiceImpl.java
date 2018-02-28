package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.demo.DemoService;

/**
 * Created by gaojianqun on 2018/2/24.
 */
public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello" + name;
    }
}
