/*
 * @Project Name: springmvcdemo
 * @File Name: Excel.java
 * @Package Name: com.test.learn.excel
 * @Date: 2017年11月30日下午6:08:10
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年11月30日下午6:08:10
 * @see
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface Excel {
	//列名
	String name() default "";
	
	/*字段名
	 */
	String variable() default "";

	//宽度
	int width() default 20;
	
	/*
	 * 行高
	 */
	int height() default 400;
	
	/*
	 * 标题头
	 */
	String head() default  "";
	

	//忽略该字段
	boolean skip() default false;
	
}
