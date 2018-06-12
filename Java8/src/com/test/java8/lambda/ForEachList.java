package com.test.java8.lambda;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gaojianqun on 2018/5/29.
 * //    //构造器引用
 //    final Car car = Car.create(Car::new);
 //    final List<Car> cars = Arrays.asList(car);
 //
 //    //静态方法的引用
 //    cars.forEach( Car::collide );
 //    cars.forEach( Car::repair );
 //
 //    final Car police = Car.create( Car::new );
 //    cars.forEach( police::follow );
 */
public class ForEachList {

    public static void main(String[] args){
        List<String> names = new ArrayList<String>();

        String str = new String("Google");
        String str1 = new String("Runoob");
        String str2 = new String("Taobao");
        String str3 = new String("Baidu");
        String str4 = new String("Sina");

//        names.add("Google");
//        names.add("Runoob");
//        names.add("Taobao");
//        names.add("Baidu");
//        names.add("Sina");

        names.add(str);
        names.add(str1);
        names.add(str2);
        names.add(str3);
        names.add(str4);

        //正常循环输出
//        names.forEach(System.out::println);

        //循环查找为Taobao的name
        names.forEach(name -> {
            if("Taobao".equals(name)){
                System.out.println(name);
            }
        });
    }

}

@FunctionalInterface
interface Supplier<T>{
    T get();
}

class Car{
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

}
