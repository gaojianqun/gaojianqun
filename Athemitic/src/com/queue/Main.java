package com.queue;

public class Main {

    public static void main(String [] args){
        ArrayQueue queue = new ArrayQueue(10);
        queue.enqueue("I");
        queue.enqueue("am");
        queue.enqueue("a");
        queue.enqueue("天才");
        for(int i = 0;i< 10 ;i++){
            System.out.print(queue.dequeue() + " ");
        }

        System.out.println();

        //解决队列浪费内存现象的方法
        queue.enqueue2("I");
        queue.enqueue2("am");
        queue.enqueue2("a");
        queue.enqueue2("天才");
        for(int i = 0;i< 10 ;i++){
            System.out.print(queue.dequeue() + " ");
        }

        System.out.println();

        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.enqueue("I");
        linkedQueue.enqueue("am");
        linkedQueue.enqueue("a");
        linkedQueue.enqueue("天才");

        for(int i = 0;i< 10 ;i++){
            System.out.print(linkedQueue.dequeue() + " ");
        }

    }

}
