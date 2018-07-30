package com.test.java8.stream;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by gaojianqun on 2018/5/29.
 * stream的使用
 */
public class StreamTest1 {

    public static void main(String[] args) throws Exception{

        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishTime = "2018-07-20 00:00:00";
        Date dateTime2 = formatter2.parse(publishTime);
        System.out.println(dateTime2);

        Date date2 = new Date(System.currentTimeMillis()); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);
        System.out.println(sDateTime1);

        Date date = new Date(1529942400000L); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

        System.out.println(sDateTime);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = formatter.parse(sDateTime);
        System.out.println(dateTime);

        String str = "2018-07-04";
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter1.parse(str);
        System.out.println(date1.getTime());


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
