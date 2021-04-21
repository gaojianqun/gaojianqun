package com.design.factory2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

enum Color{
    RED,GREEN,BLUE;//定义枚举的3个类型
}

public class Test {

    public static void main(String [] args){
        //System.out.println(index--);

        int j = 1;
        for (int i = 0; i < j; i++) {
            System.out.println(111);
        }

        Color c = Color.BLUE;//取出蓝色
        System.out.println(c);//输出信息

        int[] arr = {1, 1, 1, 4};
        //产生0-(arr.length-1)的整数值,也是数组的索引
        int index = (int) (Math.random() * arr.length);
        int boom = arr[index];
        System.out.println(boom);

        DecimalFormat df = new DecimalFormat("0.0");
        System.out.println(Float.valueOf(df.format((float) 5 / 10)));
    }

}
