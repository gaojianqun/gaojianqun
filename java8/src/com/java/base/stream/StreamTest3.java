package com.java.base.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by gaojianqun on 2018/6/12.
 * 给定一个单词列表，你想要返回另
 * 一个列表，显示每个单词中有几个字母。
 * 流支持map方法，它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映射成一个新的元素
 */
public class StreamTest3 {

    public static void main(String[] args){
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> list = words.stream().map(String::length).collect(toList());
        list.stream().forEach(a->System.out.println(a));

        //扁平流的使用
        List<String> str = Arrays.asList("Hello","World");

        List<String> uniqueCharacters = str.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueCharacters.toString());

        //例如，给定列表[1, 2, 3]和列表[3, 4]，应
        //该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> arrays = numbers1.stream()
                .flatMap(number1->numbers2.stream()
                        .map(number2-> new int[]{number1,number2})).collect(Collectors.toList());

        //返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的
        List<Integer> numbers3 = Arrays.asList(1,2,3);
        List<Integer> numbers4 = Arrays.asList(3,4);
        List<int[]> arrays2 = numbers3.stream()
                .flatMap(number3->numbers4.stream().filter(number4->(number3+number4)%3==0)
                        .map(number4-> new int[]{number3,number4})).collect(Collectors.toList());

    }

}
