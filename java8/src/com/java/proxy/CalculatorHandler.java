package com.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by gaojianqun on 2018/11/20.
 * 需求：除了对外提供的相加和相减两种方法以外要求输出before invocation和after invocation字样
 */
public class CalculatorHandler implements InvocationHandler{

    private Object object; //被代理类

    public CalculatorHandler(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("in calculatorhandler, before invocation");
        Object ret = method.invoke(object,args); //执行被代理类方法
        System.out.println("in calculatorhandler, before invocation");
        return ret;
    }
}
