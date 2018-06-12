package com.test.java8.lambda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaojianqun on 2018/5/29.
 * 循环遍历map
 */
public class ForEachMap {

    public static void main(String[] args){
        Map<String, Integer> items = new HashMap<>();
        items.put("A", 10);
        items.put("B", 20);
        items.put("C", 30);
        items.put("D", 40);
        items.put("E", 50);
        items.put("F", 60);

        Supplier<ArrayList<String>> supplier = ArrayList<String>::new;
        ArrayList<String> list = supplier.get();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.stream().forEach((x) -> {
            System.out.println(x);
        });

        //遍历map
//        items.forEach((k,v) -> System.out.println("Item:" + k + "Count:" + v));

        //打印指定map
        items.forEach((k,v) -> {
            System.out.println("Item:" + k + "Count:" + v);
            if("E".equals(k)){
                System.out.println("Hello E");
            }
        });
    }

}
