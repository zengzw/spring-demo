/*
 * @Project Name: springmvcdemo
 * @File Name: AtomicIntegerTest.java
 * @Package Name: com.test.learn.concurrent
 * @Date: 2018年5月10日上午10:25:47
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月10日上午10:25:47
 * @see
 */
public class AtomicIntegerTest {
	
	public static void main(String[] args) {

		AtomicInteger atomicInteger = new AtomicInteger(0);
		System.out.println(atomicInteger.get());
		
		System.out.println(atomicInteger.getAndSet(10));
		
		System.out.println(atomicInteger.getAndIncrement());
		System.out.println(atomicInteger.get());
	}
}
