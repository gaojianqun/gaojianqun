package com.design.factory2;

import com.sun.deploy.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Test2 {

    /**
     * 出现的概率为25%
     */
    public static double rate0 = 0.25;

    /**
     * 出现的概率为25%
     */
    public static double rate1 = 0.25;

    /**
     * 出现的概率为25%
     */
    public static double rate2 = 0.25;

    /**
     * 出现的概率为25%
     */
    public static double rate3 = 0.25;

    /**
     * Math.random()产生一个double型的随机数，判断一下
     *
     * @return int
     */
    private int PercentageRandom() {
        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= rate0) {
            return 0;
        } else if (randomNumber >= rate0 && randomNumber <= rate0 + rate1) {
            return 1;
        } else if (randomNumber >= rate0 + rate1
                && randomNumber <= rate0 + rate1 + rate2) {
            return 2;
        } else if (randomNumber >= rate0 + rate1 + rate2
                && randomNumber <= rate0 + rate1 + rate2 + rate3) {
            return 3;
        }
        return -1;
    }

    /**
     * 测试主程序
     *
     * @param agrs
     */
    public static void main(String[] agrs) {
        //java.lang.Math.random() 返回一个正符号的double值，大于或等于0.0且小于1.0
//        Test2 a = new Test2();
//        //打印10000个测试概率的准确性
//        for (int i = 0; i <= 100; i++) {
//            System.out.println(a.PercentageRandom());
//        }
//
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "2");
//        map.put("1", "2");
//
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("a");
//        String citiesCommaSeparated = String.join(",", list);
//        System.out.println(citiesCommaSeparated);
//        List<String> list1 = list.stream().filter(v -> "a".equals(v)).limit(3).collect(Collectors.toList());
//        for (String string : list1) {
//            System.out.println(string);
//        }
//        String str = "1.5w";
//        int count = getCaloriesCount(str);
//        System.out.println(count);
//
//        Calendar c = Calendar.getInstance();
//        int datenum = c.get(Calendar.DATE);
//        System.out.println(datenum);
//
//        Double d = -0.40000000000001;
//        String string = d.toString();
//        System.out.println(new Double(string));
//
//        for (int i = 0; i < 7; i++) {
//            if ((i % 2) == 0) {
//                System.out.println(11111);
//            } else {
//                System.out.println(222222);
//            }
//        }
//
////        int num = (int) Math.random() * 399 + 101;
//
//        int num = (int) Math.round(Math.random() * 399 + 101);
//        System.out.println(num);
//
//        long l = 1606914608384L;
//        int i = (int)l;
//        System.out.println(i);
//
//        long time = System.currentTimeMillis();
//        System.out.println(time/1000);
//
//        Map<String,Long> map1 = new HashMap<>();
//        Map<String,Long> map2 = new HashMap<>();
//        map2.put("free",1L);
//        Map<String,Long> map3 = new HashMap<>();
//        map3.put("browser",2L);
//        Map<String,Long> map4 = new HashMap<>();
//        map4.put("game",3L);
//        map1.putAll(map2);
//        map1.putAll(map3);
//        map1.putAll(map4);
//
//        System.out.println(map1.entrySet());


        //测试概率
//        for (int j = 0;j < 10;j++){
//            int aaa = getRate();
//            System.out.println(aaa);
//        }
//
//        List<String> list = new ArrayList<>();
//        list.add("Tom");
//        list.add("Jerray");
//        list.add("Amy");
//        for (int i = 1; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
//
//        Calendar c = Calendar.getInstance();
//        int currDay = c.get(Calendar.DAY_OF_WEEK);
//        int dayOfWork = 3;
//        c.set(Calendar.DAY_OF_WEEK, dayOfWork);
//        c.set(Calendar.HOUR, 0);
//        c.set(Calendar.MINUTE, 0);
//        c.set(Calendar.SECOND, 0);
//        if (currDay < dayOfWork) {
//            c.add(Calendar.DAY_OF_MONTH, -7);
//        }
//
//        String str = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
//        System.out.println(str);
//
//        DecimalFormat df = new DecimalFormat("0.00");
//        System.out.println(Float.valueOf(df.format((float) 19100 / 10000)));

//        InstrumentedHashSet<String> s = new InstrumentedHashSet<>(new TreeSet<>());
//        List<String> list = new ArrayList<>();
//        list.add("Snap");
//        list.add("Cracle");
//        list.add("Pop");
//        s.addAll(list);
//        System.out.println(s.getAddCount());
//
//        System.out.println(System.currentTimeMillis());
//
//        int currentDay = day(1614528000000L);
//        System.out.println("currentDay:" + currentDay);
//
//        Map<Integer, Integer> randomMap = new HashMap<>();
//        for (int i = 41; i < 53; i++) {
//            randomMap.put(i, 5);
//        }
//        System.out.println(randomMap.toString());
//
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(2);
//        list2.add(3);
//
//        List<Integer> list3 = list1.subList(0, 2);
//        list3.forEach(v -> System.out.print(v + ","));
//
//        System.out.println();
//
//        List<Integer> list4 = list1.subList(1, 2);
//        list4.forEach(v -> System.out.println(v + ","));

        int num = 0;
        for (int i = 0; i < 2000; i++) {
            int count = random(5000, 50000);
            System.out.println(count);
            num += count;
        }

        System.out.println("最终的总数为：" + num);
    }

    public static class FastPrize {
        private Integer code;
        private String desc;

        public FastPrize(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    private static int getCaloriesCount(String prizeDesc) {
        String regEx = "[^0-9]+";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(prizeDesc);
        String str = m.replaceAll("").trim();
        return Integer.valueOf(str);
    }

    /**
     * 获取最终的概率数据
     *
     * @return
     */
    public static int getRate() {
        int rate = 0;
        //循环100次提高
        for (int i = 0; i <= 100; i++) {
            rate = percentageRandom();
        }
        return rate;
    }

    interface Rate {

        //-----------第一阶段概率--------------

        /**
         * 猜中出现的概率为50%
         */
        double rate1 = 0.5;

        /**
         * 普通出现的概率为50%
         */
        double rate2 = 0.5;
    }

    /**
     * Math.random()产生一个double型的随机数，判断一下
     *
     * @return int
     */
    private static int percentageRandom() {
        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= Rate.rate1) {
            return 0;
        } else if (randomNumber > Rate.rate1 && randomNumber <= Rate.rate1 + Rate.rate2) {
            return 1;
        }
        return -1;
    }

    static class RandomUtil {
        public static int getRandom(int min, int max) {
            Random random = new Random();
            int s = random.nextInt(max) % (max - min + 1) + min;
            return s;
        }
    }

    public static int day(long time) {
        final int count = 29;
        Long start = timeMs("2021-02-01 00:00:00");
        start = (start == null) ? 0 : start;
        long timeGap = (time - start) % timeMsDay(count);
        if (timeGap < 0) {
            timeGap += timeMsDay(count);
        }
        long everyDay = timeMsDay();
        return ((int) (timeGap / everyDay)) + 1;
    }

    public static Long timeMs(String str) {
        Date time = time(str);
        return time != null ? time.getTime() : null;
    }

    public static Date time(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        str = str.trim();
        DateFormat df;

        int len = str.length();
        if (7 <= len && len <= 10) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        } else if (str.length() == 16) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (str.length() == 19) {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }

        try {
            return df.parse(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static long timeMsDay() {
        return 24 * 60 * 60 * 1000L;
    }

    public static long timeMsDay(int day) {
        return day * timeMsDay();
    }

    /**
     * 计算一个 min（包含） 到 max（包含） 之间的随机数，int型。
     */
    public static int random(int min, int max) {
        Random r = new Random();

        if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) {
            return r.nextInt();
        }

        long bound = (long) max - (long) min + 1;
        if (bound > Integer.MAX_VALUE) {
            long n = r.nextLong();
            if (n < 0) {
                n += Long.MAX_VALUE;
                n += 1;
            }
            n = n % bound;
            n += min;
            return (int) n;
        }

        return r.nextInt(max - min + 1) + min;
    }

}
