/*
 * @Project Name: springmvcdemo
 * @File Name: Filter1.java
 * @Package Name: com.test.learn.jdk8.anotation
 * @Date: 2018年1月18日上午10:41:43
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月18日上午10:41:43
 * @see
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Filter.class)
public @interface Filter1 {
	
	String value();
}
