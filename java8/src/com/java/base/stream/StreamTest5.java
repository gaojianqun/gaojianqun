package com.java.base.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by gaojianqun on 2018/6/13.
 */
public class StreamTest5 {

    public static void main(String [] args){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> list = transactions.stream().filter((t->t.getYear()==2011))
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(list.toString());

        //交易员都在哪些不同的城市工作过
        List<String> list1 = transactions.stream().map((t->t.getTrader().getCity()))
                .distinct().collect(Collectors.toList());
        System.out.println(list1.toString());

        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> list2 = transactions.stream().map(Transaction::getTrader).filter(t->"Cambridge".equals(t.getCity()))
                .sorted(Comparator.comparing(t->t.getName())).collect(Collectors.toList());
        System.out.println(list2.toString());


        //有没有交易员是在米兰工作的
        Boolean flag = transactions.stream().map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        System.out.println(flag);

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream().filter(t->"Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue).forEach(System.out::println);

    }

}

class Trader{
    private final String name;
    private final String city;
    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}

class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;
    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    public String toString(){
        return "{" + this.trader + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }
}
