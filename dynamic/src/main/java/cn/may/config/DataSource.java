package cn.may.config;

import java.lang.annotation.*;

/**
 * Created by gaojianqun on 2017/11/5.
 * Rention规定了该注解在运行时启动
 * Target说明该注解可以是给类或者方法使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface DataSource {
    String value() default "";
}
