package com.java.base.lambda;

import java.util.Arrays;

/**
 * Created by gaojianqun on 2018/5/30.
 */
public class Lambda3 {

    public static void main(String [] args){
        String [] newWay = "Improving code with Lambda expressions in Java 8".split(" ");
        Arrays.sort(newWay,(s1,s2) -> {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        });
        System.out.println(String.join(",",newWay));
    }

}
