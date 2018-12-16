package com.java.timer2;

/**
 * Created by gaojianqun on 2018/11/20.
 * 需求：除了对外提供的相加和相减两种方法以外要求输出before invocation和after invocation字样
 */
public class CalculatorImpl implements Calculator {


    @Override
    public Integer add(Integer num1, Integer num2) {
        int ret = num1 + num2;
        System.out.println("in calculatorImpl, res: " + ret);
        return ret;
    }

    @Override
    public Integer minus(Integer num1, Integer num2) {
        int ret = num1 - num2;
        System.out.println("int calculatorImpl, res: " + ret);
        return ret;
    }
}
