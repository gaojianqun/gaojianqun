package com.java.timer2;

import java.util.Map;
import java.util.Timer;

/**
 * Created by gaojianqun on 2018/11/20.
 * 需求：除了对外提供的相加和相减两种方法以外要求输出before invocation和after invocation字样
 */
public class MyTimerProxyTest {

    public static void main(String [] args){
        CalculatorImpl calculator = new CalculatorImpl();
        //创建一个Timer实例(位于java.util包下的)
        Timer timer = new Timer();
        //创建一个MyTimerTask实例
        MyTimerTask myTimerTask = new MyTimerTask(calculator);
        /**
         * 通过Timer定时定频率调用MyTimerTask的业务逻辑
         * 即第一次执行是在当前时间的两秒钟之后，之后每隔一秒钟执行一次
         */
        timer.schedule(myTimerTask,2000L,1000L);
    }

}
