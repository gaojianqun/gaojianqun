package cn.exercise;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by gaojianqun on 2018/5/4.
 * 生成的随机数
 */
public class GoodLuck {

   public static void main(String [] args){
       Random random = new Random();
       for(int i = 0;i< 25;i++){
            System.out.print(random.nextInt(25)+",");
       }

   }

}
