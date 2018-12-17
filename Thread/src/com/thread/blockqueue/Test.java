package com.thread.blockqueue;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class Test {

    public static void main(String [] args){
        ArrayQueue queue = new ArrayQueue(10);
        queue.enqueue("I");
        queue.enqueue("am");
        queue.enqueue("a");
        queue.enqueue("天才");

        for(int i = 0; i< 10;i++){
            System.out.print(queue.dequeue()+" ");
        }
    }

}
