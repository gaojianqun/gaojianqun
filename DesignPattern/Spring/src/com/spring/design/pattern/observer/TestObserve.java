package com.spring.design.pattern.observer;

/**
 * Created by gaojianqun on 2018/10/17.
 */
public class TestObserve {

    public static void main(String[] args) {

        Teacher teacher=new Teacher();
        Student zhangSan=new Student("张三", teacher);
        Student LiSi=new Student("李四", teacher);
        Student WangWu=new Student("王五", teacher);

        teacher.setHomeWork("第二页第六题");
        teacher.setHomeWork("第三页第七题");
        teacher.setHomeWork("第五页第八题");
    }

}
