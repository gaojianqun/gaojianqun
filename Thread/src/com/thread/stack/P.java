package com.thread.stack;

/**
 * Created by gaojianqun on 2018/10/16.
 * 生产者
 */
public class P implements Runnable{

    private MyStack myStack;

    public P(MyStack myStack){
        this.myStack = myStack;
    }

    @Override
    public void run() {
        myStack.push();
    }
}
