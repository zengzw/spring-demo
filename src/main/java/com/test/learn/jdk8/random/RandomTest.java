/*
 * @Project Name: springmvcdemo
 * @File Name: RandomTest.java
 * @Package Name: com.test.learn.jdk8.random
 * @Date: 2018年2月1日下午2:59:49
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8.random;

import java.util.Random;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年2月1日下午2:59:49
 * @see
 */
public class RandomTest {
	
	public static void main(String[] args) {
		for(int i = 0; i<20; i++)
		System.out.println(getRandomNumberInRange8(5, 10));
		
		System.out.println("----------");
		//随机生成1 到 10 的随机数，同时生成10个
		new Random().ints(10, 1, 10+1).forEach(System.out::println);;
	}
	
	/**
	 * 生成区间范围的随机数
	 */
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	
	/**
	 * JDK 8 生成方式
	 */
	private static int getRandomNumberInRange8(int min, int max) {
		
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		
		Random r = new Random();
		return r.ints(min,(max+1)).limit(1).findFirst().getAsInt();
	}
}
