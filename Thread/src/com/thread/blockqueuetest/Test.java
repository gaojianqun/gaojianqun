package com.thread.blockqueuetest;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class Test {

    public static void main(String[] args){
        try {
            int queueSize = 10;
            ArrayBlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(queueSize);
            Producer producer = new Producer(queue);
            Consumer consumer = new Consumer(queue);
            queue.put("I");
            queue.put("am");
            queue.put("a");
            queue.put("天才");

            Thread t1 = new Thread(producer);
            Thread t2 = new Thread(consumer);

            t1.start();
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
