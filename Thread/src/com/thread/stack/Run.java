package com.thread.stack;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class Run {

    public static void main(String [] args){
            MyStack myStack = new MyStack();
            P p = new P(myStack);
            C c = new C(myStack);
//            C c2 = new C(myStack);
//            C c3 = new C(myStack);
//            C c4 = new C(myStack);
//            C c5 = new C(myStack);

            Thread p_thread1 = new Thread(p);
            Thread c_thread1 = new Thread(c);
//            Thread c_thread2 = new Thread(c2);
//            Thread c_thread3 = new Thread(c3);
//            Thread c_thread4 = new Thread(c4);
//            Thread c_thread5 = new Thread(c5);


            p_thread1.start();
            c_thread1.start();
//            c_thread2.start();
//            c_thread3.start();
//            c_thread4.start();
//            c_thread5.start();

        }

}
