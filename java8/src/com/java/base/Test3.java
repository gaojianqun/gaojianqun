package com.java.base;

import com.sun.istack.internal.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaojianqun on 2018/11/13.
 */
public class Test3 {

    public static void main(String[] args){
        int flag = compare("1.2.3a","1.2.4b");
        System.out.println(flag);
    }

    private static int compare(String str1,String str2) {
        if (str1.hashCode() == str2.hashCode()) {
            return 0;
        }
        String[] array1 = str1.split("\\.");
        String[] array2 = str2.split("\\.");
        int length = array1.length > array2.length ? array2.length : array1.length;
        for (int len = 0; len < length; len++) {
            if (checkNum(array1[len]) && checkNum(array2[len])) {
                int result = Integer.parseInt(array1[len]) - Integer.parseInt(array2[len]);
                if (result == 0) continue;
                return result > 0 ? 1 : -1;
            } else {
                String[] temp1 = subSplit(array1[len]);
                String[] temp2 = subSplit(array2[len]);
                int subLen = temp1.length > temp2.length ? temp2.length : temp1.length;
                for (int i = 0; i < subLen; i++) {
                    boolean check1 = checkNum(temp1[i]);
                    boolean check2 = checkNum(temp2[i]);
                    if (check1 && check2) {
                        int result = Integer.parseInt(temp1[i]) - Integer.parseInt(temp2[i]);
                        if (result == 0) continue;
                        return result > 0 ? 1 : -1;

                    } else if (!check1 && !check2) {//纯单个字母比较
                        if (temp1[i].equals(temp2[i])) continue;
                        return temp1[i].compareTo(temp2[i]);
                    }
                    //数字和字符混合比较
                    if (check1) return -1;
                    if (check2) return 1;
                }
                if (temp1.length != temp2.length)
                    return temp1.length > temp2.length ? 1 : -1;
            }
        }
        if (array1.length != array2.length)
            return array1.length > array2.length ? 1 : -1;
        return 0;
    }

    private static boolean checkNum(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    private static String[] subSplit(String str) {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                number.append("|");
                number.append(str.charAt(i));
                number.append("|");
            } else {
                number.append(str.charAt(i));
            }
        }
        return number.toString().split("\\|+");
    }

}
