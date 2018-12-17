package com.thread.blockqueue;

/**
 * Created by gaojianqun on 2018/12/16.
 */
public class ArrayQueue {

    //定义队列中的元素
    private Object[] objects;

    //定义队列的长度
    private int size;
    //定义头指针
    private int head;
    //定义尾指针
    private int tail;

    public ArrayQueue(int size){
        this.size = size;
        this.objects = new Object[size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //队列增加元素
    public boolean enqueue(Object object){
        //判断队列是否已满
        if(tail == size){
            if(head == 0) {
                return false;
            }
            //避免队列出现浪费存储空间现象
            //1.挪动数据
            for(int i = head;i < tail;i++){
                objects[i-head] = objects[i];
            }
            //2.头尾指针挪动到指定位置
            head = 0;
            tail -= head;
        }
        //添加元素，尾指针向后挪动一位
        objects[tail] = object;
        ++tail;
        return true;
    }

    //队列中出元素
    public Object dequeue(){
        if(head == tail){
            return null;
        }
        //弹出队列中的头元素
        Object object = objects[head];
        ++head;
        return object;
    }

}
