package com.thread.proandcon;

/**
 * Created by gaojianqun on 2018/10/15.
 * 生产者消费者模式：信号灯法
 */
public class Player implements Runnable{

    private Movie movie;

    public Player(Movie movie){
        super();
        this.movie = movie;
    }

    @Override
    public void run() {
        for(int i = 0; i < 20 ;i ++){
            if(i % 2 == 0){
                movie.play("播放电影：唐人街探案");
            }else{
                movie.play("播放电影：红海行动");
            }
        }
    }
}
