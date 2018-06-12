package cn.exercise;

/**
 * Created by gaojianqun on 2018/5/24.
 * 测试：将字符串“a”转换为Long会是什么，
 * 前提：使用包装类Long的两种方法：parseLong和ValueOf
 * 会报如下错误：
Exception in thread "main" java.lang.NumberFormatException: For input string: "a"
 at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
 at java.lang.Long.parseLong(Long.java:589)
 at java.lang.Long.valueOf(Long.java:803)
 at cn.exercise.StringToLong.main(StringToLong.java:12)
 */
public class StringToLong {

    public static void main(String[] args){
        String str = "100.0";
        System.out.println(Long.valueOf(str));
        System.out.println(Long.parseLong(str));
    }

}
