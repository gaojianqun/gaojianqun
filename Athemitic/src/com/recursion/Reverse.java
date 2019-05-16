package com.recursion;

/**
 * 字符串反转的几种方式
 */
public class Reverse {

    public static void main(String [] args){
        //StringBuffer中的reverse方法
        String str = "I am a 天才";
        String reverseStr = new StringBuffer(str).reverse().toString();
        System.out.println(reverseStr);

        //尾指针
        String reverStr2 = reverse2(str);
        System.out.println(reverStr2);

        //头指针变换
        String reverseStr3 = reverse3(str);
        System.out.println(reverseStr3);
    }

    public static String reverse2(String string){
        char[] array = string.toCharArray();
        String reverse2 = "";
        for(int i=array.length-1; i>=0;i--){
            reverse2 += array[i];
        }
        return reverse2;
    }

    public static String reverse3(String string){
        int length = string.length();
        String reverse = "";
        for(int i=0;i<length;i++){
            reverse = string.charAt(i)+reverse;
        }
        return reverse;
    }

}
