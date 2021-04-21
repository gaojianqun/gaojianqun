package com.design;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int indes = 10;
        int currentIndex = indes -1;
        Map<String,Integer> map = new HashMap<>();
        map.put("index",indes);
        --indes;
        System.out.println(map.get("index"));
        System.out.println(currentIndex);

        long base = -20 / 10;
        System.out.println(base);
    }
}
