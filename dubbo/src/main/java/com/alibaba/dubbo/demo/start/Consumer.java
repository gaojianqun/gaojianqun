package com.alibaba.dubbo.demo.start;

/**
 * Created by gaojiqnaun on 2018/2/26.
 */

import com.alibaba.dubbo.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动消费者
 */
public class Consumer {

    public static void main(String [] args) throws Exception{
        //加载消费者配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/demo-consumer.xml"});
        //启动
        context.start();
        //消费服务
        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("World");
        //输出
        System.out.println(hello);

    }
}
