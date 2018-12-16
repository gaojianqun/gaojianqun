package com.java.timer2;

import java.util.TimerTask;

/**
 * Created by gaojianqun on 2018/11/23.
 */
public class MyTimerTask extends TimerTask{

    private CalculatorImpl calculator;

    public MyTimerTask(CalculatorImpl calculator) {
        this.calculator = calculator;
    }

    /**
     * 具体的实现
     */
    @Override
    public void run() {
        calculator.add(11,22);
    }

}
