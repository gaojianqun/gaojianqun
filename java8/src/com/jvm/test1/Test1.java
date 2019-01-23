package com.jvm.test1;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    @Test
    public void test1() {

        Map<Person,Integer> map = new HashMap<>();
        for(int i=0; i<100; i++) {
            map.put(new Person("jon"),1);
        }

        assert map.size()==1 : "map的size不为1";
    }

}
