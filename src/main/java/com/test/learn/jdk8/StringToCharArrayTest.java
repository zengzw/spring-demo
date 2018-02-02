/*
 * @Project Name: springmvcdemo
 * @File Name: StringToCharArrayTest.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年2月1日下午4:56:19
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

import java.util.stream.Stream;



/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月1日下午4:56:19
 * @see
 */
public class StringToCharArrayTest {

	public static void main(String[] args) {

		String test = "study java8";
		char[] cs = test.toCharArray();

		for(char c :cs){
			System.out.println(c);
		}
		
		Stream.of(cs).forEach(System.out::println);
		
		System.out.println("---------------");
		
		//jdk8
		test.chars().mapToObj(x -> (char)x).forEach(x -> System.out.println(x));
	}
}
