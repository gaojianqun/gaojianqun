package com.test.java8.lambda;

/**
 * Created by gaojianqun on 2018/5/29.
 * Java8Tester 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。
 * Java8Tester 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。
 * 使用 Java8Tester 表达式可以使代码变的更加简洁紧凑。
 */
public class Lambda {

    public static void main(String [] args){
        Lambda lambda = new Lambda();
        // 类型声明
        MathOperation addition = (int a,int b) -> a + b;
        //不声明类型
        MathOperation subtraction = (a,b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (a,b) -> {return a * b;};
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        // 不用括号
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        // 用括号
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        System.out.println("10 + 5 = "+lambda.operate(10,5,addition));

        System.out.println("10 - 5 = "+lambda.operate(10,5,subtraction));

        System.out.println("10 * 5 = "+lambda.operate(10,5,multiplication));

        System.out.println("10 / 5 = "+lambda.operate(10,5,division));
    }

    @FunctionalInterface
    interface MathOperation{
        int operation(int a, int b);
    }

    @FunctionalInterface
    interface GreetingService{
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
}
