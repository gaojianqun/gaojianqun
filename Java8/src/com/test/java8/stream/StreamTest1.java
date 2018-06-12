package com.test.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gaojianqun on 2018/5/29.
 * stream的使用
 */
public class StreamTest1 {

    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        Stream<Integer> stream = numbers.stream();
//        stream.filter((x) -> {
//           return x % 2 == 0;}).forEach(System.out::println);

        stream.filter((x) -> {
            return x % 2 == 0;
        }).filter((x) ->{
            return x % 3 == 0;
        }).map((x) -> {
            return x * x;
        }).forEach(System.out::println);

        //流只能遍历一次，第二次遍历流就会报错
        //stream has already been operated upon or closed
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        s.forEach(System.out::println);//报错
    }

}
