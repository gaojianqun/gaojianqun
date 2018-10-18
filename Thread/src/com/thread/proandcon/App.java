package com.thread.proandcon;

/**
 * Created by gaojianqun on 2018/10/15.
 */
public class App {

    public static void main(String [] args){
        //共享资源
        Movie m = new Movie();

        //创建两个对象
        Player p = new Player(m);
        Watcher w = new Watcher(m);

        //开辟两个线程
        new Thread(p).start();
        new Thread(w).start();

    }

}
