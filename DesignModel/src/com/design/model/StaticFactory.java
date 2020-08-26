package com.design.model;

import java.math.BigInteger;
import java.util.EnumSet;
import java.util.Set;

public class StaticFactory {

    public static void main(String [] args){
//        Set<String> set = EnumSet.of(JACK,QUEEN,KING);
        BigInteger bigInteger = BigInteger.valueOf(Integer.MAX_VALUE);
        System.out.println(bigInteger);
    }

}
