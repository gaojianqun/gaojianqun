package com.design.model;

public class SingleTon {

    public static SingleTon singleTon = new SingleTon();

    public static int count1;

    public static int count2 = 0;

    public SingleTon(){
        count1 ++;
        count2 ++;
    }

    public static SingleTon getInstance(){
        return singleTon;
    }

    public static void main(String [] args){
        SingleTon singleTon = getInstance();
        System.out.println("count1 == " + singleTon.count1);
        System.out.println("count2 == " + singleTon.count2);
    }

}
