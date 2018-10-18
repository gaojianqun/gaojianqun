package com.thread.stack;

/**
 * Created by gaojianqun on 2018/10/16.
 * 消费者
 */
public class C implements Runnable{

    private MyStack myStack;

    public C(MyStack myStack){
        this.myStack = myStack;
    }

    @Override
    public void run() {
        System.out.println("pop = " + myStack.pop());
    }
}
