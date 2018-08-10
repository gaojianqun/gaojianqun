package com.limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by gaojianqun on 2018/8/9.
 * 测试google开源工具包guava提供了限流工具类RateLimiter
 * 该类基于“令牌桶算法”
 */
public class RateLimiterDemo {

    public static void main(String [] args){
        testNoRateLimiter();
        testWitRateLimiter();
    }

    public static void testNoRateLimiter(){
        Long start = System.currentTimeMillis();
        for(int i = 0;i<100;i++){
            System.out.println("call execute.."+i);
        }

        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    public static void testWitRateLimiter(){
        Long start = System.currentTimeMillis();
        RateLimiter rateLimiter = RateLimiter.create(10.0); //每秒不超过10个任务
        for(int i = 0;i<100;i++){
            rateLimiter.acquire(50);  // 请求RateLimiter, 超过permits会被阻塞
            System.out.println("call execute.."+i);
        }

        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

}
