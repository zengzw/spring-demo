/*
 * @Project Name: springmvcdemo
 * @File Name: StreamIntTest.java
 * @Package Name: com.test.learn.jdk8.stream
 * @Date: 2018年1月19日下午12:14:04
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月19日下午12:14:04
 * @see
 */
public class StreamIntTest {
	
	public static void main(String[] args) {

		IntStream.of(1,2,3).forEach(i ->System.out.println(i));
		
		System.out.println("----IntStream.of------");
		
		IntStream.range(1, 3).forEach(i -> System.out.println(i));
		
		
		System.out.println("-----IntStream.range------");
		
		IntStream.rangeClosed(1, 3).forEach(i -> System.out.println(i));
		
		
		System.out.println("---IntStream.rangeClosed----");
		
		IntStream.iterate(0,i -> i+2).limit(6).forEach(i->System.out.println(i));;
		
		System.out.println("------IntStream.iterate---------");
		
		List<Integer> listReult = IntStream.range(0, 10).boxed().collect(Collectors.toList());
		listReult.stream().forEach(i->System.out.println(i));
		System.out.println("------ IntStream.range(0, 10).boxed().collect---------");
		
		DoubleStream stream = IntStream.range(1, 5).mapToDouble(i -> i);
	}
}
