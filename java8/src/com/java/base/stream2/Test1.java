package com.java.base.stream2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String [] args){
        List<Item> items = Arrays.asList(
                new Item("apple", 10, (23.5)),
                new Item("apple", 20, (32.5)),
                new Item("orange", 30, (13.9)),
                new Item("orange", 20, (33.5)),
                new Item("orange", 10, (63.5)),
                new Item("orange", 50, (41.5)),
                new Item("peach", 20, (26.5)),
                new Item("peach", 30, (42.5)),
                new Item("peach", 40, (24.5)),
                new Item("peach", 10, (12.5))
        );

        // 分组，计数
        Map<String, Long> counting = items.stream().collect(Collectors.groupingBy(Item::getName,Collectors.counting()));
        System.out.println(counting);

        // 分组，计数，数量
        Map<String, Integer> sum = items.stream().collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        System.out.println(sum);

        // 分组，价格
        Map<Double,List<Item>> list = items.stream().collect(Collectors.groupingBy(Item::getPrice));
        System.out.println(list);

        //分组，名字，计数价格
        Map<String,Double> map = items.stream().collect(Collectors.groupingBy(Item::getName,Collectors.summingDouble(Item::getPrice)));
        System.out.println(map);
    }

}
