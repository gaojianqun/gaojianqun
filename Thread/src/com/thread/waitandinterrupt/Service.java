package com.thread.waitandinterrupt;

/**
 * Created by gaojianqun on 2018/10/16.
 */
public class Service {

    public void testMethod(Object lock){

        synchronized (lock){
            System.out.println("begin wait() ");
            try {
                lock.wait();
                System.out.println("end wait() ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("出现异常了，因为呈wait状态的线程被interrupt了");
            }
        }
    }

}
