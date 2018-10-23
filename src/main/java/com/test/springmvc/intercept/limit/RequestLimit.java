package com.test.springmvc.intercept.limit;

import java.lang.annotation.*;

/**
 *
 * 请求限制注解，用于方法接口上
 *
 * @author zengzw
 * @date 2018/9/27 14:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface RequestLimit {

    /**
     * （许可数），一秒钟生成多少许可数。默认为10
     *
     *
     * @return
     */
    int limitPermits() default 10;


    /**
     * true:为抛弃请求; false:阻塞,排队请求
     * @return
     */
    boolean discard() default true;

    /**
     * limit key.key 为空的话，根据方法等信息生成唯一的key.
     *
     *
     * @return
     */
    String key() default  "";


}