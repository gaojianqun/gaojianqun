package com.thread.proandcon;

/**
 * Created by gaojianqun on 2018/10/15.
 * 生产者消费者模式：信号灯法
 */
public class Watcher implements Runnable{

    private Movie movie;

    public Watcher(Movie movie){
        super();
        this.movie = movie;
    }

    @Override
    public void run() {
        for(int i= 0;i < 20 ;i ++){
            movie.watch();
        }
    }

}
