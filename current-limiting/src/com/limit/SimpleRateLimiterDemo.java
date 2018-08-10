package com.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 2个请求，即2个QPS
 * 即每500ms执行一个请求
 * Created by gaojianqun on 2018/8/9.
 */
public class SimpleRateLimiterDemo {

    public static void main(String[] args){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS");

        RateLimiter rateLimiter = RateLimiter.create(2);

        while(true) {

            rateLimiter.acquire();

            System.out.println(simpleDateFormat.format(new Date()));

        }
    }

}
