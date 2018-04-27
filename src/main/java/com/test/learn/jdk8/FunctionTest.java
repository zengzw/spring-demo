/*
 * @Project Name: springmvcdemo
 * @File Name: FunctionTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年4月16日上午11:23:07
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年4月16日上午11:23:07
 * @see
 */
public class FunctionTest {
	
	public static void main(String[] args) {

		Function<Integer, Integer> name = e -> e *2;
		Function<Integer, Integer> squar = e -> e * e;
		
		//先执行name的apply，那么apply后的值，带入squar，再执行squar 的apply。
		int value = name.andThen(squar).apply(5);
		System.out.println("add then:"+value);
		
		
		//先执行squar的apply，再把squar的值，代入name apply
		int value1 = name.compose(squar).apply(5);
		System.out.println("compose:"+value1);
		
		//BiFunction 接受两个参数的运算
		BiFunction<Integer, Integer, Integer> biFunction  = (v1,v2) -> v1 + v2;
		Function<Integer, Integer> function  = (v1) -> v1 * v1;
		
		int result = biFunction.andThen(function).apply(2, 3);
		System.out.println(result);
	}
}
