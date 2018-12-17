package com.thread.blockqueuetest;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class Producer implements Runnable{

    private int queueSize = 10;

    private BlockingQueue<Object> queue;

    public Producer(ArrayBlockingQueue<Object> queue){
        this.queue = queue;
    }


    @Override
    public void run() {
        for(int i=0;i<10;i++){
           System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
        }

    }

}
