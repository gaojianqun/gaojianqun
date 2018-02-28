package com.alibaba.dubbo.demo.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gaojianqun on 2018/2/26.
 */

/**
 * 启动提供者
 */
public class Provider {

    public static void main(String [] args) throws Exception{
        //加载dubbo配置
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/demo-provider.xml"});
        //启动
        context.start();
        System.in.read();
    }

}
