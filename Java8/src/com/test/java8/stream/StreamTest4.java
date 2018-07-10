package com.test.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by gaojianqun on 2018/6/13.
 */
public class StreamTest4 {

    public static void main(String [] args){
        //anyMap的使用：检查谓词是否至少匹配一个元素
        Stream.of("d2", "a2", "a1", "b3", "c").map((x->{
           System.out.println("map:" + x);
           return x.toUpperCase();
        })).anyMatch((x->{
            System.out.println("anyMatch:" + x);
            return x.startsWith("A");
        }));

//        map:d2
//        anyMatch: D2
//        map:a2
//        anyMatch: A2

        List<String> list = Stream.of("d2", "a2", "a1", "b3", "c").map((String::toUpperCase)).collect(Collectors.toList());
        System.out.println(list.toString());

        List<String> list1 = Stream.of("d2", "a2", "a1", "b3", "c").map((x->{
            return x.toUpperCase();
        })).collect(Collectors.toList());

        System.out.println(list1.toString());

    }

}
