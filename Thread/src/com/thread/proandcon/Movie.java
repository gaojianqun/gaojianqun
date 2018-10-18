package com.thread.proandcon;

/**
 * Created by gaojianqun on 2018/10/15.
 * 生产者消费者模式：信号灯法
 * wait()等待 释放锁，直接进入到等待池，notify()之后，释放锁进入锁定池
 * sleep()不释放锁
 */
public class Movie {

    private String pic;

    private boolean flag = true;

    public synchronized void play(String pic){
        //如果flag ----> true 生产者生产，通知消费者停止消费
        //如果flag ----> false 生产者停止生产，通知消费者消费
        if(!flag){
            try {
                Thread.sleep(500L);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.pic = pic;
        this.notify();
        flag = false;
    }

    public synchronized void watch(){
        //如果flag ----> true 生产者生产，通知消费者停止消费
        //如果flag ----> false 生产者停止生产，通知消费者消费
        if(flag){
            try {
                Thread.sleep(200L);
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(pic);
        this.notify();
        flag = true;
    }

}
