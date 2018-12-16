package com.recursion;

/**
 * 斐波那契数列
 */
public class Fibonacci {

    public static void main(String [] args){
        int fn = 1,Fn = 1;
        fibonacci(fn,Fn);
    }

    public static void fibonacci(int fn,int Fn){
        int f = fn + Fn;
        if(f >  100){
            System.out.println(f);
        }else{
            fibonacci(Fn,f);
        }
    }


}
