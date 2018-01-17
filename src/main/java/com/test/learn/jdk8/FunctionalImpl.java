/*
 * @Project Name: springmvcdemo
 * @File Name: FunctionalImpl.java
 * @Package Name: com.test.learn.jdk8
 * @Date: 2018年1月17日下午5:30:17
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.learn.jdk8;

/**
 * TODO
 * @author zengzw-1220
 * @date 2018年1月17日下午5:30:17
 * @see
 */
public class FunctionalImpl implements Functional{

	@Override
	public void method() {

		System.out.println("---method1");

	}
	
	
	public static void main(String[] args) {

		Functional f = () -> {System.out.println("method 的实现");};
		
		f.method();
		f.method1();
	}}
