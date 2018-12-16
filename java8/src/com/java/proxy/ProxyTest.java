package com.java.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by gaojianqun on 2018/11/20.
 * 需求：除了对外提供的相加和相减两种方法以外要求输出before invocation和after invocation字样
 */
public class ProxyTest {

    public static void main(String [] args){
        CalculatorImpl calculatorImpl = new CalculatorImpl();//被代理类
        //要加强的操作
        CalculatorHandler calculatorHandler = new CalculatorHandler(calculatorImpl);
        //将被代理类装载到代理对象中
        Calculator calculator = (Calculator) Proxy.newProxyInstance(calculatorImpl.getClass().getClassLoader(),calculatorImpl.getClass().getInterfaces(),calculatorHandler);
        System.out.println(calculator.add(11,22));
        System.out.println(calculator.minus(11,22));
    }

}
