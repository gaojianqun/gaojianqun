package com.queue;

public class ArrayQueue {

    //队列中的元素
    private Object[] items;
    //队列的大小
    private int n;
    //定义头尾指针
    private int head = 0;
    private int tail = 0;

    //定义构造方法
    public ArrayQueue(int capacity){
        this.n = capacity;
        this.items = new Object[capacity];
    }

    //入队
    public boolean enqueue(Object object){
        //如果队列已满，入队列无意义:尾指针直接在队列的尾部
        if(tail == n){
            return false;
        }
        //头指针不用动，直接尾指针的位置添加元素
        items[tail] = object;
        ++tail;
        return true;
    }

    //入队:解决因头尾指针变化引起的队列内存浪费现象
    public boolean enqueue2(Object object){
        //如果队列已满，入队列无意义:尾指针直接在队列的尾部
        if(tail == n){
            //说明队列已满，并且已经达到满队现象
            if(head == 0){
                return false;
            }
            //将元素向前挪动:数据搬移
            for(int i = head; i< tail; i++){
                items[i-head] = items[i];
            }
            //数据搬移完成后，将头尾指针挪到到指定位置上
            tail -= head;
            head = 0;
        }
        //头指针不用动，直接尾指针的位置添加元素
        items[tail] = object;
        ++tail;
        return true;
    }

    //出队
    public Object dequeue(){
        //如果队列已空，队列中没有元素，出队无意义：头尾指针在队列的统一位置
        if(head == tail){
            return null;
        }
        //根据先进先出原则，头指针的元素出队列
        Object object = items[head];
        ++head;
        return object;
    }

}
