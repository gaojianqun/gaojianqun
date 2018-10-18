package com.java.base.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by gaojianqun on 2018/5/30.
 * Stream用法2
 */
public class StreamTest2 {

    public static void main(String[] args){

        //10个自然数的平方
        Stream<Long> natual = Stream.generate(new NaturalSupplier());
        natual.map((x) -> {
            return x * x;
        }).limit(10).forEach(System.out::println);

        //1，2,3,4,5,6的平方
        int[] array = new int[]{1,2,3,4,5,6};
        IntStream natual1 = Arrays.stream(array);
        natual1.map((x) -> {
            return x * x;
        }).limit(array.length).forEach(System.out::println);

        //测试生成斐波那契数列:取数列的前十项
        Stream<Long> natual2 = Stream.generate(new FibonacciSupplier());
        natual2.limit(10).forEach(System.out::println);

        //测试生成斐波那契数列：取数列的20~30项
        List<Long> stream = natual2.skip(10).limit(10).collect(Collectors.toList());
        stream.forEach(System.out::println);

    }

}

class NaturalSupplier implements Supplier<Long>{

    long value = 0;

    @Override
    public Long get() {
        this.value = this.value + 1;
        return this.value;
    }

}

//生成斐波那契数列
class FibonacciSupplier implements Supplier<Long>{

    long a = 0;
    long b = 1;

    @Override
    public Long get() {
        long x = a + b;
        a = b;
        b = x;
        return a;
    }
}