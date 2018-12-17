package com.thread.blockqueuetest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class Consumer implements Runnable{

    private BlockingQueue<Object> queue;

    public Consumer(ArrayBlockingQueue<Object> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){
            try {
                System.out.println("从队列取走一个元素:" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("队列剩余"+queue.size()+"个元素");
        }
    }
}
