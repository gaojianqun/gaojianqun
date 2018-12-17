package com.thread.blockqueue;

import java.util.LinkedList;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class BlockingQueue {

   private ArrayQueue queue = new ArrayQueue(10);

   private int limit;


   public BlockingQueue(int limit){
       this.limit = limit;
   }

   //添加元素方法
   public boolean enqueue(Object object){
       //如果队列已满则等待
       while (queue.getSize() == limit){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       if(queue.getSize() == 0){
           notifyAll();
       }
       queue.enqueue(object);
       return true;
   }

   //删除元素方法
   public synchronized Object dequeue(){
       while(queue.getSize() == 0){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       if(queue.getSize() == this.limit){
           notifyAll();
       }
       Object object = queue.dequeue();
       return object;
   }

}
