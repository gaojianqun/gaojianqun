package com.spring.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    //代码的增强类
    final static MySpect ms = new MySpect();

    public static Object createProxy(final Object us){
        Object object = Proxy.newProxyInstance(us.getClass().getClassLoader(), us.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //要增强的代码
                ms.pre();
                Object object1 = method.invoke(us,args);
                //要增强的代码
                ms.last();
                return object1;
            }
        });

        //返回代理对象
        return object;
    }

}
