package com.java.base;

import java.util.*;

/**
 * Created by gaojianqun on 2018/10/18.
 * 统计 AAABBBBBBccccdde中出现字母最多的次数
 */
public class Test1 {

    public static void main(String[] args){
        String str = "AAABBBBBBccccdde";
        char[] chars = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for(int i = 0; i< chars.length;i++){
            set.add(chars[i]);
        }

        set.stream().forEach(s -> {
            int count = 0;
            for(int j = 0; j<chars.length;j++){
                if(s.equals(chars[j])) {
                    count++;
                }
            }
            System.out.println(s + "出现的次数为：" + count);
        });


    }

}
